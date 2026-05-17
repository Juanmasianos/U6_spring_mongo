package com.accesodatos.dto.instructordetail;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class InstructorDetailRequestDTO {
	
	@Size(max = 50)
	@NotBlank( message = "Youtube channel cannot be blank")
	private String youtubeChannel;

	@Size(max = 50)
	private String hobby;
}
