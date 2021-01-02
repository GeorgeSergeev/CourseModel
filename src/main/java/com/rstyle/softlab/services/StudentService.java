package com.rstyle.softlab.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rstyle.softlab.models.Course;
import com.rstyle.softlab.models.Student;
import com.rstyle.softlab.repository.StudentRepository;

@Service
public class StudentService {

	@Autowired
	private StudentRepository repo;
	
	public List<Student> findAll(){
		return (List<Student>) repo.findAll();
	}
	
	public Student save(Student student) {
		return repo.save(student);
	}
	
	public void delete(Student student) {
		repo.delete(student);
	}
	
	public Student getById(Long id) {
		return repo.findById(id).get();
	}
	
	public void assignCourse(Course course) {
		
	}
}
