package com.accesodatos.dto.instructor;

import java.util.ArrayList;
import java.util.List;

import com.accesodatos.dto.course.CourseResponseDTO;
import com.accesodatos.dto.instructordetail.InstructorDetailResponseDTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstructorResponseDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	
	private InstructorDetailResponseDTO detail;
	
	private List<CourseResponseDTO> courses = new ArrayList<>();
}
