package com.accesodatos.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.accesodatos.dto.instructor.InstructorRequestDTO;
import com.accesodatos.dto.instructor.InstructorResponseDTO;
import com.accesodatos.entity.Instructor;
import com.accesodatos.exception.ResourceNotFoundException;
import com.accesodatos.mappers.InstructorMapper;
import com.accesodatos.repository.InstructorRepository;
import com.accesodatos.service.InstructorService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class InstructorServiceImpl implements InstructorService {
	
	private final InstructorRepository instructorRepository;
	private final InstructorMapper instructorMapper;
	
	
	
	@Override
	public InstructorResponseDTO createInstructor(InstructorRequestDTO instructorRequestDTO) {
		
		Instructor instructor = instructorMapper.toEntity(instructorRequestDTO);
		Instructor savedInstructor = instructorRepository.save(instructor);
		return instructorMapper.toResponseDto(savedInstructor);
		
	}

	@Override
	public List<InstructorResponseDTO> getAllInstructors() {
		
		return instructorRepository.findAll()
				 .stream()
				 .map(instructorMapper::toResponseDto)
				 .toList();
	}

	@Override
	public InstructorResponseDTO getInstructorById(Long instructorId) {
		
		Instructor instructor = instructorRepository.findById(instructorId)
									.orElseThrow(() -> new ResourceNotFoundException("Instructor with id " + instructorId + " not found"));
		return instructorMapper.toResponseDto(instructor);
	}

	@Override
	@Transactional
	public InstructorResponseDTO updateInstructorById(Long instructorId, InstructorRequestDTO instructorRequest) {
		
		Instructor instructor = instructorRepository.findById(instructorId)
								.orElseThrow(() -> new ResourceNotFoundException("Instructor with id " + instructorId + " not found"));
		
		if (instructorRequest.getDetail() != null) {
			
			instructor.getInstructorDetail().setYoutubeChannel(instructorRequest.getDetail().getYoutubeChannel());
			instructor.getInstructorDetail().setHobby(instructorRequest.getDetail().getHobby());
			
		}
		
		instructor.setFirstName(instructorRequest.getFirstName());
		instructor.setLastName(instructorRequest.getLastName());
		instructor.setEmail(instructorRequest.getEmail());
		
		Instructor savedInstructor = instructorRepository.save(instructor);
		
		return instructorMapper.toResponseDto(savedInstructor);
		
	}

	@Override
	@Transactional
	public void deleteInstructorById(Long instructorId) {

		Instructor instructor = instructorRepository.findById(instructorId)
								.orElseThrow(() -> new ResourceNotFoundException("Instructor with id " + instructorId + " not found"));
		
		if (instructor.getCourses() != null && !instructor.getCourses().isEmpty()) {
		
			return;		
			
		}
		
		instructorRepository.delete(instructor);
		
	}


	
	

}
