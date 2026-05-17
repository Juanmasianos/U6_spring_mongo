package com.accesodatos.service;

import java.util.List;

import com.accesodatos.dto.course.CourseRequestDTO;
import com.accesodatos.dto.course.CourseResponseDTO;

public interface CourseService {

	CourseResponseDTO createCourse(CourseRequestDTO courseRequestDTO);
	
	List<CourseResponseDTO> getAllCourses();
	CourseResponseDTO getCourseById(Long courseId);
	
	void deleteCourse(Long courseId);
	CourseResponseDTO updateCourse(Long courseId, CourseRequestDTO courseRequestDTO);

	CourseResponseDTO addStudentInCourse(Long courseId, Long studentId);

	CourseResponseDTO removeStudentInCourse(Long courseId, Long studentId);
}
