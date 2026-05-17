package com.accesodatos.dto.student;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentRequestDTO {
	
	@NotBlank(message = "First Name not be blank")
	@Size(min = 3, max = 25)
	private String firstName;
	
	@Size(max = 50)
	private String lastName;
	
	@NotBlank(message = "Email cannot be blank")
	@Size(min = 8, max = 50)
	@Email(message = "not valid email")
	private String email;
	
}
