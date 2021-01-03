package com.rstyle.softlab.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rstyle.softlab.models.Course;
import com.rstyle.softlab.models.CourseResults;
import com.rstyle.softlab.models.Professor;
import com.rstyle.softlab.models.Student;
import com.rstyle.softlab.projections.CustomProjection;
import com.rstyle.softlab.services.CourseResultsService;
import com.rstyle.softlab.services.CourseService;
import com.rstyle.softlab.services.ProfessorService;
import com.rstyle.softlab.services.StudentService;
import com.rstyle.softlab.util.Excel;

@RestController("/")
public class MainController {
	
	@Autowired private StudentService stdSrvc;
	@Autowired private CourseResultsService crStvc;
	@Autowired private CourseService cSrvc;
	@Autowired private ProfessorService pSrvc;
	
	@GetMapping
	public String main() {
		return "Heeey";
	}
	
	@GetMapping("/findAllStudents")
	public List<Student> findAllStudents(){
		return stdSrvc.findAll();
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
	
	@GetMapping("/insertCourse")
	public Course insertCourse(String name, Integer number, Float price) {
		Course course = new Course();
		course.setName(name);
		course.setNumber(number);
		course.setPrice(price);
		return cSrvc.save(course);
	}
	
	@GetMapping("/insertProfessor")
	public Professor insertProfessor(String name, String address, Float payment, String phone) {
		Professor professor = new Professor();
		professor.setName(name);
		professor.setAddress(address);
		professor.setPayment(payment);
		professor.setPhone(phone);
		return pSrvc.save(professor);
	}
	
	@GetMapping("/addStudentToCourse")
	public String insertCourseResults(Long courseID, Long studentID) {
		Course course = cSrvc.getById(courseID);
		Student student = stdSrvc.getById(studentID);
		
		CourseResults cr = new CourseResults();
		cr.setStudent(student);
		cr.setCourse(course);
		crStvc.save(cr);
		return "ok";
	}
	
	@GetMapping("/removeStudentFromCourse")
	public String removeCourseResults(Long courseID, Long studentID) {
		Student student = new Student();
		student.setId(studentID);
		
		Course course = new Course();
		course.setCourse_id(courseID);
		
		CourseResults cr = crStvc.findByStudentAndCourse(student, course);
		crStvc.delete(cr);
		return "ok";
	}
	
	@GetMapping("/successRate")
	public ResponseEntity<InputStreamResource> successRate(Long courseID, Long studentID) throws FileNotFoundException {
		List<CustomProjection> data = crStvc.successRate();
		
		File file = Excel.createAndWriteToFile(data);
        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
		
        return ResponseEntity.ok()
        		.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .contentLength(file.length()) 
                .body(resource);
	}
}
