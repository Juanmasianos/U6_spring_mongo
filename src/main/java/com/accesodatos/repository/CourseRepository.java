package com.accesodatos.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.accesodatos.entity.Course;

@Repository
public interface CourseRepository extends MongoRepository<Course, Long> {

}
