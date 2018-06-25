package com.klochan.course.service;

import com.klochan.course.dao.CourseDao;
import com.klochan.course.dao.ProfessorDao;
import com.klochan.course.dao.StudentDao;
import com.klochan.course.model.Course;
import com.klochan.course.model.Professor;
import com.klochan.course.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class UniversityService {

	private final CourseDao courseDao;
	private final ProfessorDao professorDao;
	private final StudentDao studentDao;

	@Autowired
	public UniversityService( CourseDao courseDao, ProfessorDao professorDao, StudentDao studentDao ) {
		this.courseDao = courseDao;
		this.professorDao = professorDao;
		this.studentDao = studentDao;
	}

	public void enrollStudentToCourse( Student student, Course course ) {
		if ( studentDao.existsById( student.getStudentId() ) && courseDao.existsById( course.getCourseId() ) ) {
			student.enrollTo( course );
			studentDao.save( student );
		}
		else throw new EntityNotFoundException();
	}

	public void completeCourse( Student student, Course course ) {
		if ( !studentDao.existsById( student.getStudentId() ) || !courseDao.existsById( course.getCourseId() ) ||
		     !student.getActiveCourses().contains( course ) ) throw new EntityNotFoundException();
		else student.complete( course );
	}

	public void addScore( Student student, Course course, int score ) {
		if ( !studentDao.existsById( student.getStudentId() ) || !courseDao.existsById( course.getCourseId() ) ||
		     !student.getActiveCourses().contains( course ) ) throw new EntityNotFoundException();
		else student.getCourseStatus( course ).addScore( score );
	}

	public void addCourseToProfessor( Course course, Professor professor ) {
		if ( !courseDao.existsById( course.getCourseId() ) || !professorDao.existsById( professor.getProfessorId() ) )
			throw new EntityNotFoundException();
		else {
			professor.addCourse( course );
			course.addProfessor( professor );
		}
	}

}
