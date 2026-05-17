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

import com.accesodatos.dto.course.CourseRequestDTO;
import com.accesodatos.dto.course.CourseResponseDTO;
import com.accesodatos.service.CourseService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
@Tag(
		name = "Course Controller",
		description = "Endpoints para la gestión de cursos"
)
public class CourseController {

	private static final String COURSE_RESOURCE = "/courses";
	private static final String COURSE_ID_PATH = COURSE_RESOURCE + "/{courseId}";
	private static final String COURSE_STUDENT_ID_PATH = COURSE_RESOURCE + "/{courseId}" + "/students/{studentId}";
	
	CourseService courseService;

	@Operation(
			summary = "Ping pong",
			description = "Endpoint de prueba."
	)
	@ApiResponse(
			responseCode = "200",
			description = "pong"
	)
	@GetMapping(COURSE_RESOURCE + "/ping")
	public ResponseEntity<String> pong() {
		
		return ResponseEntity.ok().body("pong course..");
		
	}

	@Operation(
			summary = "Crear curso",
			description = "Crea un nuevo curso.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Curso creado exitosamente"),
	})
	@PostMapping(value = COURSE_RESOURCE,
				 consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<CourseResponseDTO> createourse(@Valid @RequestBody CourseRequestDTO courseRequestDTO) {
		
		CourseResponseDTO createdCourse = courseService.createCourse(courseRequestDTO);
		return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
	}

	@Operation(
			summary = "Listar cursos",
			description = "Obtiene una lista de todos los cursos."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de cursos recuperada correctamente")
	})
	@GetMapping(value = COURSE_RESOURCE,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
		List<CourseResponseDTO> courses = courseService.getAllCourses();
		
		return new ResponseEntity<>(courses, HttpStatus.OK);
		
	}

	@Operation(
			summary = "Obtener curso por ID",
			description = "Obtiene un curso mediante ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Curso encontrado"),
			@ApiResponse(responseCode = "404", description = "El curso no existe")
	})
	@GetMapping(value = COURSE_ID_PATH,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long courseId) {
		CourseResponseDTO course = courseService.getCourseById(courseId);
		
		return new ResponseEntity<>(course, HttpStatus.OK);
		
	}

	@Operation(
			summary = "Actualizar curso",
			description = "Actualiza un curso por su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Curso actualizado correctamente"),
	})
	@PutMapping(value = COURSE_ID_PATH,
			produces = MediaType.APPLICATION_JSON_VALUE,
			consumes = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<CourseResponseDTO> updateCourseById(@PathVariable Long courseId, @Valid @RequestBody CourseRequestDTO courseRequestDto) {
		
		CourseResponseDTO updatedCourse = courseService.updateCourse(courseId, courseRequestDto);
		
		return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
		
	}

	@Operation(
			summary = "Matricular estudiante",
			description = "Asocia un estudiante a un curso.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Estudiante añadido con éxito"),
	})
	@PostMapping(value = COURSE_STUDENT_ID_PATH,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<CourseResponseDTO> addStudentInCourse(@PathVariable Long courseId, @PathVariable Long studentId){
		
		return new ResponseEntity<>(courseService.addStudentInCourse(courseId, studentId), HttpStatus.OK);
		
	}

	@Operation(
			summary = "Desvincular estudiante",
			description = "Elimina a un estudiante de un curso.")
	@ApiResponses(value = {
			@ApiResponse(
					responseCode = "200",
					description = "Estudiante removido con éxito"),
	})
	@PutMapping(value = COURSE_STUDENT_ID_PATH,
			produces = MediaType.APPLICATION_JSON_VALUE
	)
	public ResponseEntity<CourseResponseDTO> removeStudentInCourse(@PathVariable Long courseId, @PathVariable Long studentId){
		
		return new ResponseEntity<>(courseService.removeStudentInCourse(courseId, studentId), HttpStatus.OK);
		
	}

	@Operation(
			summary = "Eliminar curso",
			description = "Elimina un curso por su ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Curso eliminado correctamente"),
	})
	@DeleteMapping(value = COURSE_ID_PATH)
	public ResponseEntity<Void> delete(@PathVariable Long courseId) {
		
		courseService.deleteCourse(courseId);
		return ResponseEntity.noContent().build();
		
	}
}
