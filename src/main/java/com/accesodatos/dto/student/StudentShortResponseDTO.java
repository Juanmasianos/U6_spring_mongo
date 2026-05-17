package com.accesodatos.dto.student;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentShortResponseDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	
}
