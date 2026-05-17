package com.accesodatos.dto.instructor;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstructorShortResponseDTO {

	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	
}
