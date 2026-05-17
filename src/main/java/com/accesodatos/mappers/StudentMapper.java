package com.accesodatos.mappers;

import org.springframework.stereotype.Component;

import com.accesodatos.dto.student.StudentRequestDTO;
import com.accesodatos.dto.student.StudentResponseDTO;
import com.accesodatos.dto.student.StudentShortResponseDTO;
import com.accesodatos.entity.Student;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class StudentMapper {

	public Student toEntity(StudentRequestDTO dto) {
		Student student = Student.builder()
								 .firstName(dto.getFirstName())
								 .lastName(dto.getLastName())
								 .email(dto.getEmail())
								 .build();
				
		return student;
	}
	
	public StudentResponseDTO toResponseDto(Student student) {
		StudentResponseDTO dto = new StudentResponseDTO();
		
		dto.setId(student.getId());
		dto.setFirstName(student.getFirstName());
		dto.setLastname(student.getLastName());
		dto.setEmail(student.getEmail());

		return dto;
	}
	
	public StudentShortResponseDTO toShortResponseDto(Student student) {
		
		StudentShortResponseDTO dto = new StudentShortResponseDTO();
		
		dto.setId(student.getId());
		dto.setFirstName(student.getFirstName());
		dto.setLastName(student.getLastName());
		dto.setEmail(student.getEmail());
		
		return dto;
		
	}
	
}
