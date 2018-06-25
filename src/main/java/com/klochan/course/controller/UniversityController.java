package com.klochan.course.controller;

import com.klochan.course.model.Course;
import com.klochan.course.model.Professor;
import com.klochan.course.model.Student;
import com.klochan.course.service.CourseService;
import com.klochan.course.service.ProfessorService;
import com.klochan.course.service.StudentService;
import com.klochan.course.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UniversityController {

	private final UniversityService universityService;
	private final StudentService studentService;
	private final CourseService courseService;
	private final ProfessorService professorService;

	@Autowired
	public UniversityController( UniversityService universityService, StudentService studentService,
	                             CourseService courseService, ProfessorService professorService ) {
		this.universityService = universityService;
		this.studentService = studentService;
		this.courseService = courseService;
		this.professorService = professorService;
	}

	@PostMapping( "/students/{studentId}/courses" )
	public void enroll( @PathVariable int studentId, @RequestParam int courseId ) {
		Student student = studentService.getStudentById( studentId );
		Course course = courseService.getCourseById( courseId );
		universityService.enrollStudentToCourse( student, course );
	}

	@PostMapping( "/courses/{courseId}/" )
	public void complete( @PathVariable int courseId, @RequestParam int studentId ) {
		Student student = studentService.getStudentById( studentId );
		Course course = courseService.getCourseById( courseId );
		universityService.completeCourse( student, course );
	}

	@PostMapping( "/students/{studentId}/scores" )
	public void addScore( @PathVariable int studentId, @RequestParam int courseId, @RequestParam int score ) {
		Student student = studentService.getStudentById( studentId );
		Course course = courseService.getCourseById( courseId );
		universityService.addScore( student, course, score );
	}

	@PostMapping( "/professors/{professorId}/courses" )
	public void addCourseToProfessor( @PathVariable int professorId, @RequestParam int courseId ) {
		Professor professor = professorService.getProfessorById( professorId );
		Course course = courseService.getCourseById( courseId );
		universityService.addCourseToProfessor( course, professor );
	}

	@GetMapping( "/tables/{tableName}" )
	public ModelAndView studentsPage( @PathVariable String tableName ) {
		return new ModelAndView( tableName + "Table" );
	}
}
