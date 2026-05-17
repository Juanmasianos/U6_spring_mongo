package com.accesodatos.service;

import java.util.List;

import com.accesodatos.dto.student.StudentRequestDTO;
import com.accesodatos.dto.student.StudentResponseDTO;
import com.accesodatos.dto.student.StudentShortResponseDTO;

public interface StudentService {

	public StudentResponseDTO createStudent(StudentRequestDTO student);

	public List<StudentShortResponseDTO> getAllStudents();

	public StudentResponseDTO getStudentById(Long studentId);

	public StudentResponseDTO updateStudentById(Long studentId, StudentRequestDTO studentRequestDTO);

	public void deleteStudent(Long studentId);
	
}
