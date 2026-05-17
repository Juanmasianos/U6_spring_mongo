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

import com.accesodatos.dto.student.StudentRequestDTO;
import com.accesodatos.dto.student.StudentResponseDTO;
import com.accesodatos.dto.student.StudentShortResponseDTO;
import com.accesodatos.service.StudentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Tag(
		name = "Student Controller",
		description = "Endpoints para la gestión de alumnos"
)
public class StudentController {

	private static final String STUDENT_RESOURCE = "/students";
	private static final String STUDENT_ID_PATH = STUDENT_RESOURCE + "/{studentId}";
	
	StudentService studentService;

	@Operation(
			summary = "Ping pong",
			description = "Endpoint de prueba"
	)
	@ApiResponse(
			responseCode = "200",
			description = "pong"
	)
	@GetMapping(STUDENT_RESOURCE + "/ping")
	public ResponseEntity<String> pong() {
		
		return ResponseEntity.ok().body("pong student..");
		
	}

	@Operation(
			summary = "Listar estudiantes",
			description = "Obtiene una lista con todos los estudiantes."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de estudiantes recuperada correctamente")
	})
	@GetMapping(value = STUDENT_RESOURCE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StudentShortResponseDTO>> getAllStudents() {
	
		List<StudentShortResponseDTO> students = studentService.getAllStudents();
	
		return new ResponseEntity<>(students, HttpStatus.OK);
	
	}

	@Operation(
			summary = "Obtener estudiante por ID",
			description = "Obtiene un estudiante mediante su ID."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Estudiante encontrado"),
			@ApiResponse(responseCode = "404", description = "El estudiante no existe")
	})
	@GetMapping(value = STUDENT_ID_PATH,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long studentId) {
	
		return new ResponseEntity<>(studentService.getStudentById(studentId), HttpStatus.OK);
	
	}

	@Operation(
			summary = "Crear estudiante",
			description = "Crea un nuevo alumno"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Estudiante creado")
	})
	@PostMapping(value = STUDENT_RESOURCE,
				 consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<StudentResponseDTO> createourse(@Valid @RequestBody StudentRequestDTO studentRequestDTO) {
		
		StudentResponseDTO createdStudent = studentService.createStudent(studentRequestDTO);
		return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
	}

	@Operation(
			summary = "Actualizar estudiante",
			description = "Actualiza la informacion de un estudiante.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Estudiante actualizado correctamente"),
			@ApiResponse(responseCode = "404", description = "Estudiante no encontrado")
	})
	@PutMapping(value = STUDENT_ID_PATH,
			 consumes = MediaType.APPLICATION_JSON_VALUE,
			 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<StudentResponseDTO> updateCourseById(@PathVariable Long studentId, @Valid @RequestBody StudentRequestDTO studentRequestDTO) {
	
		StudentResponseDTO updatedStudent = studentService.updateStudentById(studentId, studentRequestDTO);
		return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
	}

	@Operation(
			summary = "Eliminar estudiante",
			description = "Elimina un estudiante.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Estudiante eliminado correctamente"),
			@ApiResponse(responseCode = "404", description = "El estudiante no existe")
	})
	@DeleteMapping(value = STUDENT_ID_PATH)
	public ResponseEntity<Void> delete(@PathVariable Long studentId) {
		
		studentService.deleteStudent(studentId);
		return ResponseEntity.noContent().build();
		
	}
}
