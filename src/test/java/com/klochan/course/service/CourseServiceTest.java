package com.klochan.course.service;

import com.klochan.course.dao.CourseDao;
import com.klochan.course.model.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

import static com.klochan.course.TestData.getCourse;
import static com.klochan.course.TestData.getCourseWithProfessor;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith( SpringExtension.class )
@SpringBootTest
@Transactional
class CourseServiceTest {

	@Autowired
	private CourseService courseService;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private CourseDao courseDao;

	@BeforeEach
	public void resetIds() {
		entityManager.createNativeQuery( "ALTER TABLE Student AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE Course AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE Professor AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE CourseStatus AUTO_INCREMENT = 1" ).executeUpdate();
	}

	@Test
	void testGetAllCoursesShouldReturnEmptyList() {
		assertEquals( List.of(), courseService.getAllCourses() );

		courseDao.saveAll( List.of( getCourse( 1 ), getCourse( 2 ), getCourse( 3 ) ) );
		courseDao.findAll().forEach( s -> System.out.println( s.getCourseId() ) );
	}

	@Test
	void testGetAllCoursesShouldReturnListSize2() {
		List< Course > expected = List.of( getCourse( 1 ), getCourse( 2 ) );
		courseDao.saveAll( expected );

		List< Course > actual = courseService.getAllCourses();

		assertIterableEquals( expected, actual );
	}


	@Test
	void testGetAllCoursesWithLimitShouldReturnEmptyList() {
		int start = 1;
		int limit = 2;

		List< Course > actual = courseService.getAllCourses( start, limit );

		assertEquals( List.< Course >of(), actual );
	}

	@Test
	void testGetAllCoursesWithLimitShouldReturnListSize2() {
		int start = 0;
		int limit = 2;
		List< Course > expected = List.of( getCourse( 1 ), getCourse( 2 ) );
		courseDao.saveAll( expected );

		List< Course > actual = courseService.getAllCourses( start, limit );

		assertEquals( expected, actual );
	}

	@Test
	void testGetCourseByIdShouldReturnCourse() {
		int id = 1;
		Course expected = getCourse( id );
		courseDao.save( expected );
		Course actual = courseService.getCourseById( id );

		assertEquals( expected, actual );
	}

	@Test
	void testGetCourseByIdShouldThrowNotFound() {
		assertThrows( EntityNotFoundException.class, () -> courseService.getCourseById( 1 ) );
	}

	@Test
	void testAddCourseShouldReturnId() {
		int id = 1;
		assertEquals( id, courseService.addCourse( getCourse( id ) ) );
	}

	@Test
	void testAddCourseWithProfessorShouldReturnId() {
		int courseId = 1;
		int professorId = 1;
		assertEquals( professorId, courseService.addCourse( getCourseWithProfessor( courseId, professorId ) ) );
	}


	@Test
	void testUpdateCourseShouldReturnId() {
		int courseId = 1;
		Course course = getCourse( courseId );
		Course updatedCourse = getCourse( courseId );
		updatedCourse.setName( "updatedName" );
		updatedCourse.setCost( 321 );
		updatedCourse.setNumber( 12 );

		courseDao.save( course );
		courseService.updateCourse( courseId, updatedCourse );

		assertEquals( updatedCourse, courseDao.findById( courseId ).orElseThrow() );
	}

	@Test
	void testUpdateCourseShouldThrowEntityNotFoundException() {
		int courseId = 1;
		int professorId = 1;
		Course course = getCourse( courseId );
		Course courseWithProfessor = getCourseWithProfessor( courseId, professorId );

		courseDao.save( course );
		assertThrows( EntityNotFoundException.class, () -> courseService.updateCourse( 2, courseWithProfessor ) );
	}


	@Test
	void testDeleteCourseByIdShouldReturnVoid() {
		courseDao.saveAll( List.of( getCourse( 1 ), getCourse( 2 ) ) );
		courseService.deleteCourseById( 1 );
		assertEquals( 1, courseDao.count() );
	}

	@Test
	void testDeleteCourseByIdShouldThrowEntityNotFoundException() {
		assertThrows( EntityNotFoundException.class, () -> courseService.deleteCourseById( 1 ) );
	}

	@Test
	void testDeleteAllCoursesShouldReturnVoid() {
		courseDao.saveAll( List.of( getCourse( 1 ), getCourse( 2 ) ) );
		courseService.deleteAllCourses();
		assertEquals( 0, courseDao.count() );
	}
}



