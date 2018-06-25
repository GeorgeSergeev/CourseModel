package com.klochan.course.exception;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class EntityValidationException extends RuntimeException {

	private List< String > errors;

	public < T > EntityValidationException( Set< ConstraintViolation< T > > validationResults ) {
		errors = validationResults.stream()
		                          .map( v -> v.getPropertyPath().toString() + ": " + v.getMessage() )
		                          .collect( Collectors.toList() );
	}

	public EntityValidationException( List< ObjectError > allErrors ) {
		//todo
	}
}
