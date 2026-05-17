package com.accesodatos.entity;

import java.util.List;
import java.util.ArrayList;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "students")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 25)
	private String firstName;
	
	@Column(length = 50)
	private String lastName;
	
	@Column(unique = true, length = 50)
	private String email;
	
	@ManyToMany(mappedBy = "students")
	@Builder.Default
	private List<Course> courses = new ArrayList<>();
	
	//helpers
	public void addCourse(Course course) {
		this.courses.add(course);
		course.getStudents().add(this);
	}
	
	public void removeCourse(Course course) {
		this.courses.remove(course);
		course.getStudents().add(this);
	}
	
}
