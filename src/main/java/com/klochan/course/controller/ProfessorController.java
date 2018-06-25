package com.klochan.course.controller;

import com.klochan.course.exception.EntityValidationException;
import com.klochan.course.model.Professor;
import com.klochan.course.service.ProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProfessorController {

	private ProfessorService professorService;

	@Autowired
	public ProfessorController( ProfessorService professorService ) {
		this.professorService = professorService;
	}

	@GetMapping( "/professors" )
	public List< Professor > getProfessors() {
		return professorService.getAllProfessors();
	}

	@GetMapping( value = "/professors", params = { "start", "limit" } )
	public List< Professor > getProfessorsWithLimit( @RequestParam int start, @RequestParam int limit ) {
		return professorService.getAllProfessors( start, limit );
	}

	@GetMapping( "/professors/{professorId}" )
	public Professor getProfessor( @PathVariable int professorId ) {
		return professorService.getProfessorById( professorId );
	}

	@PostMapping( "/professors" )
	@ResponseStatus( HttpStatus.CREATED )
	public Professor addProfessor( @RequestBody @Valid Professor professor, BindingResult bindingResult ) {
		if ( bindingResult.hasErrors() ) throw new EntityValidationException( bindingResult.getAllErrors() );
		professorService.addProfessor( professor );
		return professor;
	}

	@PutMapping( "/professors/{professorId}" )
	public Professor updateProfessor( @PathVariable int professorId, @RequestBody @Valid Professor professor,
	                             BindingResult bindingResult ) {
		if ( bindingResult.hasErrors() ) throw new EntityValidationException( bindingResult.getAllErrors() );
		professorService.updateProfessor( professorId, professor );
		return professorService.getProfessorById( professorId );
	}

	@DeleteMapping( "/professors/{professorId}" )
	@ResponseStatus( HttpStatus.NO_CONTENT )
	public void deleteProfessor( @PathVariable int professorId ) {
		professorService.deleteProfessorById( professorId );
	}

	@DeleteMapping( "/professors" )
	@ResponseStatus( HttpStatus.NO_CONTENT )
	public void deleteProfessors() {
		professorService.deleteAllProfessors();
	}
}
