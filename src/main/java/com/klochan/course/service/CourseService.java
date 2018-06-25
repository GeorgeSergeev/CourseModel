package com.klochan.course.service;

import com.klochan.course.dao.CourseDao;
import com.klochan.course.model.Course;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CourseService {

	private final CourseDao courseDao;

	@Autowired
	public CourseService( CourseDao courseDao ) {
		this.courseDao = courseDao;
	}

	@Transactional( readOnly = true )
	public List< Course > getAllCourses() {
		return Lists.newArrayList( courseDao.findAll() );
	}

	@Transactional( readOnly = true )
	public List< Course > getAllCourses( int start, int limit ) {
		return Lists.newArrayList( courseDao.findAll( PageRequest.of( start, limit ) ) );
	}

	@Transactional( readOnly = true )
	public Course getCourseById( int courseId ) {
		return courseDao.findById( courseId ).orElseThrow( EntityNotFoundException::new );
	}

	public int addCourse( Course course ) {
		return courseDao.save( course ).getCourseId();
	}

	public void updateCourse( int courseId, Course course ) {
		Course saved = courseDao.findById( courseId ).orElseThrow( EntityNotFoundException::new );
		saved.setName( course.getName() );
		saved.setNumber( course.getNumber() );
		saved.setCost( course.getCost() );
		courseDao.save( saved );
	}

	public void deleteCourseById( int courseId ) {
		if ( !courseDao.existsById( courseId ) ) throw new EntityNotFoundException();
		courseDao.delete( getCourseById( courseId ) );
	}

	public void deleteAllCourses() {
		courseDao.deleteAll();
	}
}
