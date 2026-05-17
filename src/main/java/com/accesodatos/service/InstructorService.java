package com.accesodatos.service;

import java.util.List;

import com.accesodatos.dto.instructor.InstructorRequestDTO;
import com.accesodatos.dto.instructor.InstructorResponseDTO;

public interface InstructorService {
	
	InstructorResponseDTO createInstructor(InstructorRequestDTO instructorResquestDTO);

	List<InstructorResponseDTO> getAllInstructors();

	InstructorResponseDTO getInstructorById(Long id);
	
	InstructorResponseDTO updateInstructorById(Long instructorId, InstructorRequestDTO instructorRequest);

	void deleteInstructorById(Long instructorId);

}
