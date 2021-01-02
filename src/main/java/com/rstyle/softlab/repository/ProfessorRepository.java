package com.rstyle.softlab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rstyle.softlab.models.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Long>{
	
}
