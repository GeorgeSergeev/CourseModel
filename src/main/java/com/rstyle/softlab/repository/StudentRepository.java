package com.rstyle.softlab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rstyle.softlab.models.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long>{
}
