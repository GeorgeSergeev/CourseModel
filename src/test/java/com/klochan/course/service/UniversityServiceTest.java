package com.klochan.course.service;

import com.klochan.course.dao.CourseDao;
import com.klochan.course.dao.ProfessorDao;
import com.klochan.course.dao.StudentDao;
import com.klochan.course.model.Course;
import com.klochan.course.model.Professor;
import com.klochan.course.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import static com.klochan.course.TestData.*;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith( SpringExtension.class )
@SpringBootTest
@Transactional
class UniversityServiceTest {

	@Autowired
	private UniversityService universityService;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private StudentDao studentDao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private ProfessorDao professorDao;

	@BeforeEach
	public void resetIds() {
		entityManager.createNativeQuery( "ALTER TABLE Student AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE Course AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE Professor AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE CourseStatus AUTO_INCREMENT = 1" ).executeUpdate();
	}

	@Test
	void testEnrollToCourse() {
		int studentId = 1;
		int courseId = 1;
		Student student = getStudent( studentId );
		Course course = getCourse( courseId );
		studentDao.save( student );
		courseDao.save( course );

		universityService.enrollStudentToCourse( student, course );

		assertThat( student.getCourseStatusList().size(), is( 1 ) );
		assertThat( student.getCourseStatus( course ).getCourse(), is( course ) );
		assertThat( course.getStudents().size(), is( 1 ) );
		assertThat( course.getStudents(), hasItem( student ) );
	}

	@Test
	void testEnrollToCourseShouldThrowEntityNotFoundException() {
		int studentId = 1;
		int courseId = 1;
		Student student = getStudent( studentId );
		Course course = getCourse( courseId );

		assertThrows( EntityNotFoundException.class, () -> universityService.enrollStudentToCourse( student,
		                                                                                            course ) );
	}

	@Test
	void testCompleteCourse() {
		int studentId = 1;
		int courseId = 1;
		Student student = getStudentWithCourse( studentId, courseId );
		Course course = student.getActiveCourses().get( 0 );
		studentDao.save( student );

		universityService.completeCourse( student, course );

		assertThat( student.getActiveCourses().size(), is( 0 ) );
		assertThat( student.getCompletedCourses().size(), is( 1 ) );
		assertThat( student.getCourseStatus( course ).isCompleted(), is( true ) );
	}

	@Test
	void testCompleteCourseShouldThrowEntityNotFoundException() {
		int studentId = 1;
		int courseId = 1;
		Student student = getStudentWithCourse( studentId, courseId );
		Course course = student.getActiveCourses().get( 0 );

		assertThrows( EntityNotFoundException.class, () -> universityService.completeCourse( student, course ) );
	}

	@Test
	void testAddScore() {
		int studentId = 1;
		int courseId = 1;
		Student student = getStudentWithCourse( studentId, courseId );
		Course course = student.getActiveCourses().get( 0 );
		studentDao.save( student );

		universityService.addScore( student, course, 100 );

		assertThat( student.getActiveCourses().size(), is( 1 ) );
		assertThat( student.getCompletedCourses().size(), is( 0 ) );
		assertThat( student.getCourseStatus( course ).getScores().size(), is( 1 ) );
		assertThat( student.getCourseStatus( course ).getScores().get( 0 ), is( 100 ) );
	}


	@Test
	void testAddScoreShouldThrowEntityNotFoundException() {
		int studentId = 1;
		int courseId = 1;
		Student student = getStudentWithCourse( studentId, courseId );
		Course course = student.getActiveCourses().get( 0 );

		assertThrows( EntityNotFoundException.class, () -> universityService.addScore( student, course, 100 ) );
	}


	@Test
	void testAddCourseToProfessor() {
		int professorId = 1;
		int courseId = 1;
		Professor professor = getProfessor( professorId );
		Course course = getCourse( courseId );
		courseDao.save( course );
		professorDao.save( professor );

		universityService.addCourseToProfessor( course, professor );

		assertThat( course.getProfessors().size(), is( 1 ) );
		assertThat( course.getProfessors(), hasItem( professor ) );
		assertThat( professor.getCourses().size(), is( 1 ) );
		assertThat( professor.getCourses(), hasItem( course ) );
	}

	@Test
	void testAddCourseToProfessorShouldThrowEntityNotFoundException() {
		int professorId = 1;
		int courseId = 1;
		Professor professor = getProfessor( professorId );
		Course course = getCourse( courseId );

		assertThrows( EntityNotFoundException.class,
		              () -> universityService.addCourseToProfessor( course, professor ) );
	}
}