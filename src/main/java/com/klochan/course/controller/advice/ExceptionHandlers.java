package com.klochan.course.controller.advice;

import com.klochan.course.exception.EntityValidationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionHandlers {

	@ExceptionHandler( { EntityNotFoundException.class, IllegalArgumentException.class } )
	public ResponseEntity entityNotFound() {
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler( EntityValidationException.class )
	public ResponseEntity entityNotCorrect( EntityValidationException e ) {
		return ResponseEntity.badRequest().body( e.getErrors() );
	}
}
