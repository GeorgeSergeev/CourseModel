package com.rstyle.softlab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rstyle.softlab.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
}
