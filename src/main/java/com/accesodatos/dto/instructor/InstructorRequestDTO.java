package com.accesodatos.dto.instructor;

import com.accesodatos.dto.instructordetail.InstructorDetailRequestDTO;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstructorRequestDTO {
	
	@NotBlank(message = "First Name not be blank")
	@Size(min = 3, max = 25)
	private String firstName;
	
	@Size(max = 50)
	private String lastName;
	
	@NotBlank(message = "Email cannot be blank")
	@Size(min = 8, max = 50)
	@Email(message = "not valid email")
	private String email;
	
	@Valid
	@Nullable
	private InstructorDetailRequestDTO detail;
}
