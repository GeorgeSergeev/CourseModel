package com.klochan.course.controller;

import com.klochan.course.exception.EntityValidationException;
import com.klochan.course.model.Student;
import com.klochan.course.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class StudentController {

	private StudentService studentService;

	@Autowired
	public StudentController( StudentService studentService ) {
		this.studentService = studentService;
	}

	@GetMapping( "/students" )
	public List< Student > getStudents() {
		return studentService.getAllStudents();
	}

	@GetMapping( value = "/students", params = { "start", "limit" } )
	public List< Student > getStudentsWithLimit( @RequestParam int start, @RequestParam int limit ) {
		return studentService.getAllStudents( start, limit );
	}

	@GetMapping( "/students/{studentId}" )
	public Student getStudent( @PathVariable int studentId ) {
		return studentService.getStudentById( studentId );
	}

	@PostMapping( "/students" )
	@ResponseStatus( HttpStatus.CREATED )
	public Student addStudent( @Valid @RequestBody Student student, BindingResult bindingResult ) {
		if ( bindingResult.hasErrors() ) throw new EntityValidationException( bindingResult.getAllErrors() );
		studentService.addStudent( student );
		return student;
	}

	@PutMapping( "/students/{studentId}" )
	public Student updateStudent( @PathVariable int studentId, @Valid @RequestBody Student student,
	                              BindingResult bindingResult ) {
		if ( bindingResult.hasErrors() ) throw new EntityValidationException( bindingResult.getAllErrors() );
		studentService.updateStudent( studentId, student );
		return studentService.getStudentById( studentId );
	}

	@DeleteMapping( "/students/{studentId}" )
	@ResponseStatus( HttpStatus.NO_CONTENT )
	public void deleteStudent( @PathVariable int studentId ) {
		studentService.deleteStudentById( studentId );
	}

	@DeleteMapping( "/students" )
	@ResponseStatus( HttpStatus.NO_CONTENT )
	public void deleteStudents() {
		studentService.deleteAllStudents();
	}


}
