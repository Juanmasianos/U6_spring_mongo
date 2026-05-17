package com.accesodatos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.accesodatos.dto.course.CourseRequestDTO;
import com.accesodatos.dto.course.CourseResponseDTO;
import com.accesodatos.dto.instructor.InstructorShortResponseDTO;
import com.accesodatos.entity.Course;
import com.accesodatos.entity.Instructor;
import com.accesodatos.entity.Student;
import com.accesodatos.exception.ResourceNotFoundException;
import com.accesodatos.mappers.CourseMapper;
import com.accesodatos.mappers.InstructorMapper;
import com.accesodatos.repository.CourseRepository;
import com.accesodatos.repository.InstructorRepository;
import com.accesodatos.repository.StudentRepository;
import com.accesodatos.service.CourseService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class CourseServiceImpl implements CourseService {

	CourseRepository courseRepository;
	InstructorRepository instructorRepository;
	StudentRepository studentRepository;
	
	CourseMapper courseMapper;
	InstructorMapper instructorMapper;

	
	@Override
	@Transactional
	public CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO) {
		//localizar instructor por id
		Instructor instructor = instructorRepository
										.findById(courseRequestDTO.getInstructorId())
										.orElseThrow(() -> new ResourceNotFoundException(
															"Intructor not found"
															));
		
		//crear la entidad course
		Course course = new Course();
		course.setTitle(courseRequestDTO.getTitle());
		course.setInstructor(instructor);
		
		//guardar
		Course savedCourse = courseRepository.save(course);
		
		//mappear al DTO
		CourseResponseDTO responseDto = courseMapper.toResponseDto(savedCourse);
		
		InstructorShortResponseDTO instructorShort = instructorMapper.toShortResponseDto(instructor);
		
		responseDto.setInstructor(instructorShort);
		responseDto.setStudents(new ArrayList<>());
		
		return responseDto;
		
	}


	@Override
	public List<CourseResponseDTO> getAllCourses() {
		List<CourseResponseDTO> listOfCourses = courseRepository.findAll()
																.stream()
																.map(courseMapper::toResponseDto)
																.toList();
		
		return listOfCourses;
	}


	@Override
	public CourseResponseDTO getCourseById(Long courseId) {
		Course course = courseRepository.findById(courseId)
						.orElseThrow(() -> new ResourceNotFoundException("Course with id " + courseId + " not found"));
		
		return courseMapper.toResponseDto(course);
	}


	@Override
	@Transactional
	public void deleteCourse(Long courseId) {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course with id " + courseId + " not found"));
		
		// desvincular los students
		for (Student student: course.getStudents()) {
			student.getCourses().remove(course);
		}
		
		course.getStudents().clear();
		
		//intructor
		Instructor instructor = course.getInstructor();
		
		if (instructor != null ) {
			instructor.removeCourse(course);
			course.setInstructor(null);
		}
		
		//guardar los cambios
		courseRepository.save(course);
		
		// Eliminar el curso
		courseRepository.delete(course);
		
	}


	@Override
	@Transactional
	public CourseResponseDTO updateCourse(Long courseId, CourseRequestDTO courseRequestDTO) {
		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course with id " + courseId + " not found"));
		
		Instructor newInstructor = instructorRepository.findById(courseRequestDTO.getInstructorId())
				.orElseThrow(() -> new ResourceNotFoundException("Instructor with id " + courseRequestDTO.getInstructorId() + " not found"));
		
		course.setTitle(courseRequestDTO.getTitle());
		
		Instructor oldInstructor = course.getInstructor();
		//si existe, eliminar el curso de su lista
		
		if (oldInstructor != null && oldInstructor.getCourses() != null) {
			oldInstructor.getCourses().remove(course);
		}
		
		course.setInstructor(newInstructor);
		
		Course saved = courseRepository.save(course);
		
		return courseMapper.toResponseDto(saved);
		
	}


	@Override
	@Transactional
	public CourseResponseDTO addStudentInCourse(Long courseId, Long studentId) {

		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course with id " + courseId + " not found"));

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " not found"));
		
		for (Course studentCourse: student.getCourses()) {
			if (studentCourse.getId() == course.getId()) {
				return courseMapper.toResponseDto(course);	
			}
		}
		
		course.addStudent(student);
		
		Course updatedCourse = courseRepository.save(course);
		
		return courseMapper.toResponseDto(updatedCourse);
	}

	@Override
	@Transactional
	public CourseResponseDTO removeStudentInCourse(Long courseId, Long studentId) {

		Course course = courseRepository.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course with id " + courseId + " not found"));

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " not found"));
		
		
		if (!course.getStudents().contains(student)) {
			return courseMapper.toResponseDto(course);	
		}
				
		course.removeStudent(student);
			
		Course updatedCourse = courseRepository.save(course);

		return courseMapper.toResponseDto(updatedCourse);	
		
	}

}
