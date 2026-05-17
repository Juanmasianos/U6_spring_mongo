package com.accesodatos.mappers;

import org.springframework.stereotype.Component;

import com.accesodatos.dto.course.CourseResponseDTO;
import com.accesodatos.dto.course.CourseShortResponseDTO;
import com.accesodatos.entity.Course;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CourseMapper {
	
	private final InstructorMapper instructorMapper;
	private final StudentMapper studentMapper;
	
	public CourseResponseDTO toResponseDto(Course course) {
		
		CourseResponseDTO dto = new CourseResponseDTO();
		
		dto.setId(course.getId());
		dto.setTitle(course.getTitle());
		
		if (course.getInstructor() != null) {
			
			dto.setInstructor(instructorMapper.toShortResponseDto(course.getInstructor()));
			
		}
		
		dto.setStudents(
				course.getStudents()
					  .stream()
					  .map(studentMapper::toShortResponseDto)
					  .toList()
			);
		return dto;
	}
	
	public CourseShortResponseDTO toShortResponseDto(Course course) {
		
		CourseShortResponseDTO dto = new CourseShortResponseDTO();
		
		dto.setId(course.getId());
		dto.setTitle(course.getTitle());
		
		return dto;
	}
}
