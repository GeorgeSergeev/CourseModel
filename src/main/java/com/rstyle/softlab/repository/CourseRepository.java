package com.rstyle.softlab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rstyle.softlab.models.Course;

@Repository
public interface CourseRepository extends CrudRepository<Course, Long>{

}
