package com.rstyle.softlab.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rstyle.softlab.models.Course;
import com.rstyle.softlab.repository.CourseRepository;

@Service
public class CourseService {

	@Autowired
	private CourseRepository repo;
	
	public Course save(Course enrty) {
		return repo.save(enrty);
	}
	
	public void delete(Course enrty) {
		repo.delete(enrty);
	}
	
	public Course getById(Long id) {
		return repo.findById(id).get();
	}
}
