package com.rstyle.softlab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rstyle.softlab.repository.ProfessorRepository;

@Service
public class ProfesorService {

	@Autowired
	private ProfessorRepository repo;
}
