package com.klochan.course.controller;

import com.klochan.course.exception.EntityValidationException;
import com.klochan.course.model.Course;
import com.klochan.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CourseController {

	private CourseService courseService;

	@Autowired
	public CourseController( CourseService courseService ) {
		this.courseService = courseService;
	}

	@GetMapping( "/courses" )
	public List< Course > getCourses() {
		return courseService.getAllCourses();
	}

	@GetMapping( value = "/courses", params = { "start", "limit" } )
	public List< Course > getCoursesWithLimit( @RequestParam int start, @RequestParam int limit ) {
		return courseService.getAllCourses( start, limit );
	}

	@GetMapping( "/courses/{courseId}" )
	public Course getCourse( @PathVariable int courseId ) {
		return courseService.getCourseById( courseId );
	}

	@PostMapping( "/courses" )
	@ResponseStatus( HttpStatus.CREATED )
	public Course addCourse( @RequestBody @Valid Course course, BindingResult bindingResult ) {
		if ( bindingResult.hasErrors() ) throw new EntityValidationException( bindingResult.getAllErrors() );
		courseService.addCourse( course );
		return course;
	}

	@PutMapping( "/courses/{courseId}" )
	public Course updateCourse( @PathVariable int courseId, @RequestBody @Valid Course course,
	                            BindingResult bindingResult ) {
		if ( bindingResult.hasErrors() ) throw new EntityValidationException( bindingResult.getAllErrors() );
		courseService.updateCourse( courseId, course );
		return courseService.getCourseById( courseId );
	}

	@DeleteMapping( "/courses/{courseId}" )
	@ResponseStatus( HttpStatus.NO_CONTENT )
	public void deleteCourse( @PathVariable int courseId ) {
		courseService.deleteCourseById( courseId );
	}

	@DeleteMapping( "/courses" )
	@ResponseStatus( HttpStatus.NO_CONTENT )
	public void deleteCourses() {
		courseService.deleteAllCourses();
	}
}
