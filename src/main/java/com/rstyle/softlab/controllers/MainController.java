package com.rstyle.softlab.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rstyle.softlab.models.Course;
import com.rstyle.softlab.models.CourseResults;
import com.rstyle.softlab.models.Student;
import com.rstyle.softlab.services.CourseResultsService;
import com.rstyle.softlab.services.CourseService;
import com.rstyle.softlab.services.StudentService;

@RestController("/")
public class MainController {
	
	@Autowired private StudentService stdSrvc;
	@Autowired private CourseResultsService crStvc;
	@Autowired private CourseService cSrvc;
	
	@GetMapping
	public String main() {
		return "Heeey";
	}
	
	@GetMapping("/insertStudent")
	public Student insertStudent(String name, Integer bookNumber, String phone, String address, String email){
		Student student = new Student();
		student.setName(name);
		student.setStudentRecordBookNumber(bookNumber);
		student.setPhone(phone);
		student.setAddress(address);
		student.setEmail(email);
		return stdSrvc.save(student);
	}
	
	@GetMapping("/findAllStudents")
	public List<Student> findAllStudents(){
		return stdSrvc.findAll();
	}
	
	@GetMapping("/insertCourseResults")
	public String insertCourseResults(Long courseID, Long studentID) {
		Course course = cSrvc.getById(courseID);
		Student student = stdSrvc.getById(studentID);
		
		CourseResults cr = new CourseResults();
		cr.setStudent(student);
		cr.setCourse(course);
		crStvc.save(cr);
		return "ok";
	}
	
	@GetMapping("/insertCourse")
	public Course insertCourse(String name, Integer number, Float price) {
		Course course = new Course();
		course.setName(name);
		course.setNumber(number);
		course.setPrice(price);
		return cSrvc.save(course);
	}
}
