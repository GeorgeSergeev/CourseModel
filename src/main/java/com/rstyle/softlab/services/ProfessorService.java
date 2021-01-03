package com.rstyle.softlab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rstyle.softlab.models.Professor;
import com.rstyle.softlab.repository.ProfessorRepository;

@Service
public class ProfessorService {

	@Autowired
	private ProfessorRepository repo;
	
	public List<Professor> findAll(){
		return (List<Professor>) repo.findAll();
	}
	
	public Professor save(Professor professor) {
		return repo.save(professor);
	}
	
	public void delete(Professor professor) {
		repo.delete(professor);
	}
	
	public Professor getById(Long id) {
		return repo.findById(id).get();
	}
}
