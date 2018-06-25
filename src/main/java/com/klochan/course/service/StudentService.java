package com.klochan.course.service;

import com.klochan.course.dao.StudentDao;
import com.klochan.course.model.Student;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@Transactional
public class StudentService {

	private final StudentDao studentDao;

	@Autowired
	public StudentService( StudentDao studentDao ) {
		this.studentDao = studentDao;
	}

	@Transactional( readOnly = true )
	public List< Student > getAllStudents() {
		return Lists.newArrayList( studentDao.findAll() );
	}

	@Transactional( readOnly = true )
	public List< Student > getAllStudents( int start, int limit ) {
		return Lists.newArrayList( studentDao.findAll( PageRequest.of( start, limit ) ) );
	}

	@Transactional( readOnly = true )
	public Student getStudentById( int studentId ) {
		return studentDao.findById( studentId ).orElseThrow( EntityNotFoundException::new );
	}

	public int addStudent( Student student ) {
		return studentDao.save( student ).getStudentId();
	}

	public void updateStudent( int studentId, Student student ) {
		Student saved = studentDao.findById( studentId ).orElseThrow( EntityNotFoundException::new );
		saved.setName( student.getName() );
		saved.setAddress( student.getAddress() );
		saved.setPhone( student.getPhone() );
		saved.setEmail( student.getEmail() );
		saved.setCreditBook( student.getCreditBook() );
		studentDao.save( saved );
	}

	public void deleteStudentById( int studentId ) {
		if ( !studentDao.existsById( studentId ) ) throw new EntityNotFoundException();
		studentDao.delete( getStudentById( studentId ) );
	}

	public void deleteAllStudents() {
		studentDao.deleteAll();
	}
}
