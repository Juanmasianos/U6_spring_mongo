package com.accesodatos.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Table(name = "Instructors")
public class Instructor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false, length = 25)
	private String firstName;
	
	@Column(length = 50)
	private String lastName;
	
	@Column(unique = true, length = 50)
	private String email;
	
	@CreationTimestamp
	private Date createdOn;
	
	// relacion unidireccional (simple primary key)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "instructor_detail_id")
	private InstructorDetail instructorDetail;
	
	@OneToMany(
			mappedBy = "instructor",
			cascade = { CascadeType.PERSIST, CascadeType.MERGE }
	)
	@Builder.Default
	private List<Course> courses = new ArrayList<>();
	
	//helpers
	public void addCourse(Course course) {
		this.courses.add(course);
		course.setInstructor(this);
	}
	
	public void removeCourse(Course course) {
		this.courses.remove(course);
		course.setInstructor(null);
	}
}
