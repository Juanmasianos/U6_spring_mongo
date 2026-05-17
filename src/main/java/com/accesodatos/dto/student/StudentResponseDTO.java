package com.accesodatos.dto.student;

import java.util.ArrayList;
import java.util.List;

import com.accesodatos.dto.course.CourseShortResponseDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentResponseDTO {

	private Long id;
	private String firstName;
	private String lastname;
	private String email;
	
	private List<CourseShortResponseDTO> courses = new ArrayList<>();
	
}
