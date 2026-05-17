package com.accesodatos.mappers;

import org.springframework.stereotype.Component;

import com.accesodatos.dto.instructor.InstructorRequestDTO;
import com.accesodatos.dto.instructor.InstructorResponseDTO;
import com.accesodatos.dto.instructor.InstructorShortResponseDTO;
import com.accesodatos.dto.instructordetail.InstructorDetailResponseDTO;
import com.accesodatos.entity.Instructor;
import com.accesodatos.entity.InstructorDetail;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class InstructorMapper {
	
	public InstructorShortResponseDTO toShortResponseDto(Instructor instructor) {
		InstructorShortResponseDTO dto = new InstructorShortResponseDTO();

		dto.setId(instructor.getId());
		dto.setFirstName(instructor.getFirstName());
		dto.setLastName(instructor.getLastName());
		dto.setEmail(instructor.getEmail());
		
		return dto;
	}

	public Instructor toEntity(InstructorRequestDTO dto) {
		
		Instructor inst = Instructor.builder()
									.firstName(dto.getFirstName())
									.lastName(dto.getLastName())
									.email(dto.getEmail())
									.build();
		if(dto.getDetail() != null) {
			InstructorDetail detail = new InstructorDetail();
			
			detail.setYoutubeChannel(dto.getDetail().getYoutubeChannel());
			detail.setHobby(dto.getDetail().getHobby());
			
			inst.setInstructorDetail(detail);
		}
		
		return inst;
	}
	
	public InstructorResponseDTO toResponseDto(Instructor instructor) {

		InstructorResponseDTO response = new InstructorResponseDTO();
		
		response.setId(instructor.getId());
		response.setFirstName(instructor.getFirstName());
		response.setLastName(instructor.getLastName());
		response.setEmail(instructor.getEmail());
		
		if (instructor.getInstructorDetail() != null) {
			
			InstructorDetailResponseDTO detailDto = new InstructorDetailResponseDTO();			
			
			detailDto.setId(instructor.getInstructorDetail().getId());
			detailDto.setYoutubeChannel(instructor.getInstructorDetail().getYoutubeChannel());
			detailDto.setHobby(instructor.getInstructorDetail().getHobby());
			
			response.setDetail(detailDto);
			
		}
		
		return response;
		
	}
	
}
