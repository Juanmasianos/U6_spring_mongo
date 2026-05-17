package com.accesodatos.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.accesodatos.dto.student.StudentRequestDTO;
import com.accesodatos.dto.student.StudentResponseDTO;
import com.accesodatos.dto.student.StudentShortResponseDTO;
import com.accesodatos.entity.Course;
import com.accesodatos.entity.Student;
import com.accesodatos.exception.ResourceNotFoundException;
import com.accesodatos.mappers.StudentMapper;
import com.accesodatos.repository.StudentRepository;
import com.accesodatos.service.StudentService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {
	
	StudentRepository studentRepository;
	StudentMapper studentMapper;
	


	@Override
	public StudentResponseDTO createStudent(StudentRequestDTO studentRequestDTO) {
		Student student = studentMapper.toEntity(studentRequestDTO);
		
		// Evitar emails duplicados
		if (studentRepository.existsByEmail(studentRequestDTO.getEmail())) {
			throw new IllegalArgumentException("A Student with this email already exists");
		}
		
		Student saveStudent = studentRepository.save(student);
		
		StudentResponseDTO dto = studentMapper.toResponseDto(saveStudent);
		dto.setCourses(new ArrayList<>());
		
		return dto;
				
	}



	@Override
	public List<StudentShortResponseDTO> getAllStudents() {
		
		return studentRepository.findAll().stream().map(studentMapper::toShortResponseDto).toList();
		
	}



	@Override
	public StudentResponseDTO getStudentById(Long studentId) {
		
		Student Student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " not found"));
		
		return studentMapper.toResponseDto(Student);
	}
	
	@Override
	@Transactional
	public StudentResponseDTO updateStudentById(Long studentId, StudentRequestDTO studentRequest) {
		
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " not found"));
		
		student.setFirstName(studentRequest.getFirstName());
		student.setLastName(studentRequest.getLastName());
		student.setEmail(studentRequest.getEmail());
		
		Student savedStudent = studentRepository.save(student);
		
		return studentMapper.toResponseDto(savedStudent);
	}



	@Override
	@Transactional
	public void deleteStudent(Long studentId) {

		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new ResourceNotFoundException("Student with id " + studentId + " not found"));
		
		for (Course course: student.getCourses()) {
			course.getStudents().remove(student);
		}
		
		student.getCourses().clear();
		
		studentRepository.save(student);
		
		studentRepository.delete(student);
		
	}

}
