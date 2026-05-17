package com.accesodatos.dto.course;


import java.util.ArrayList;
import java.util.List;

import com.accesodatos.dto.customer.StudentShortResponseDTO;
import com.accesodatos.dto.instructor.InstructorShortResponseDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseResponseDTO {

	private Long id;
	private String title;
	
	private InstructorShortResponseDTO instructor;
	private List<StudentShortResponseDTO> students = new ArrayList<>();
}
