package com.accesodatos.dto.course;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseRequestDTO {

	@NotBlank(message = "Title cannot be blank")
	@Size(min = 3, max = 25)
	private String title;
	
	@NotNull(message = "the instructor must be set")
	@Positive
	private Long instructorId;
	
}
