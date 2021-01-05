package com.rstyle.softlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rstyle.softlab.models.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

}
