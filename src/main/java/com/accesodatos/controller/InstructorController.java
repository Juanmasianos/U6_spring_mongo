package com.accesodatos.controller;


import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.accesodatos.dto.instructor.InstructorRequestDTO;
import com.accesodatos.dto.instructor.InstructorResponseDTO;
import com.accesodatos.service.InstructorService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Tag(
		name = "Instructor Controller",
		description = "Endpoints para la gestión de instructores"
)
public class InstructorController {

	private static final String INSTRUCTOR_RESOURCE = "/instructors";
	private static final String INSTRUCTOR_ID_PATH = INSTRUCTOR_RESOURCE + "/{instructorId}";
	
	InstructorService instructorService;

	@Operation(
			summary = "Ping pong",
			description = "Endpoint de prueba"
	)
	@ApiResponse(
			responseCode = "200",
			description = "pong"
	)
	@GetMapping(INSTRUCTOR_RESOURCE + "/ping")
	public ResponseEntity<String> pong() {
		
		return ResponseEntity.ok().body("pong instuctors..");
		
	}

	@Operation(
			summary = "Listar instructores",
			description = "Obtiene una lista de todos los instructores")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de instructores recuperada correctamente")
	})
	@GetMapping(value = INSTRUCTOR_RESOURCE,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<InstructorResponseDTO>> getAllIntructors() {
		
		List<InstructorResponseDTO> instructors = instructorService.getAllInstructors();
		
		return new ResponseEntity<>(instructors, HttpStatus.OK);
		
	}

	@Operation(
			summary = "Obtener instructor por ID",
			description = "Recupera un instructor mediante su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Instructor encontrado"),
			@ApiResponse(responseCode = "404", description = "El instructor no existe")
	})
	@GetMapping(value = INSTRUCTOR_ID_PATH,
				produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InstructorResponseDTO> getIntructorById(@PathVariable Long instructorId) {
		
		return new ResponseEntity<>(instructorService.getInstructorById(instructorId), HttpStatus.OK);
		
	}

	@Operation(
			summary = "Crear instructor",
			description = "Crea un nuevo instructor en la base de datos."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Instructor creado exitosamente"),
	})
	@PostMapping(value = INSTRUCTOR_RESOURCE,
				 consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<InstructorResponseDTO> createInstructor(@Valid @RequestBody InstructorRequestDTO instructorRequestDTO) {
		
		InstructorResponseDTO createdInstructor = instructorService.createInstructor(instructorRequestDTO);
		return new ResponseEntity<>(createdInstructor, HttpStatus.CREATED);
	}

	@Operation(
			summary = "Actualizar instructor",
			description = "Modifica la información de un instructor."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Instructor actualizado correctamente"),
			@ApiResponse(responseCode = "404", description = "Instructor no encontrado")
	})
	@PutMapping(value = INSTRUCTOR_ID_PATH,
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<InstructorResponseDTO> updateIntructorById(@PathVariable Long instructorId, @Valid @RequestBody InstructorRequestDTO instructorRequest) {
	
		InstructorResponseDTO updatedInstructor = instructorService.updateInstructorById(instructorId, instructorRequest);
		
		return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
	
	}

	@Operation(
			summary = "Eliminar instructor",
			description = "Elimina a un instructor.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Instructor eliminado correctamente"),
			@ApiResponse(responseCode = "404", description = "El instructor no existe")
	})
	@DeleteMapping(value = INSTRUCTOR_ID_PATH)
	public ResponseEntity<Void> deleteIntructorById(@PathVariable Long instructorId) {
	
		instructorService.deleteInstructorById(instructorId);
		
		return ResponseEntity.noContent().build();
	
	}
}
