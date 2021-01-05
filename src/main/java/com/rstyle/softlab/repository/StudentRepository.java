package com.rstyle.softlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rstyle.softlab.models.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
}
