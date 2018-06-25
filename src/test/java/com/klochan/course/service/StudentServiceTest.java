package com.klochan.course.service;

import com.klochan.course.dao.StudentDao;
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
import java.util.List;

import static com.klochan.course.TestData.getStudent;
import static com.klochan.course.TestData.getStudentWithCourse;
import static com.klochan.course.TestData.getStudentWithCourseWithProfessor;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith( SpringExtension.class )
@SpringBootTest
@Transactional
class StudentServiceTest {

	@Autowired
	private StudentService studentService;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private StudentDao studentDao;

	@BeforeEach
	public void resetIds() {
		entityManager.createNativeQuery( "ALTER TABLE Student AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE Course AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE Professor AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE CourseStatus AUTO_INCREMENT = 1" ).executeUpdate();
	}

	@Test
	void testGetAllStudentsShouldReturnEmptyList() {
		assertEquals( List.of(), studentService.getAllStudents() );

		studentDao.saveAll( List.of( getStudent( 1 ), getStudent( 2 ), getStudent( 3 ) ) );
		studentDao.findAll().forEach( s -> System.out.println( s.getStudentId() ) );
	}

	@Test
	void testGetAllStudentsShouldReturnListSize2() {
		List< Student > expected = List.of( getStudent( 1 ), getStudent( 2 ) );
		studentDao.saveAll( expected );

		List< Student > actual = studentService.getAllStudents();

		assertIterableEquals( expected, actual );
	}


	@Test
	void testGetAllStudentsWithLimitShouldReturnEmptyList() {
		int start = 1;
		int limit = 2;

		List< Student > actual = studentService.getAllStudents( start, limit );

		assertEquals( List.< Student >of(), actual );
	}

	@Test
	void testGetAllStudentsWithLimitShouldReturnListSize2() {
		int start = 0;
		int limit = 2;
		List< Student > expected = List.of( getStudent( 1 ), getStudent( 2 ) );
		studentDao.saveAll( expected );

		List< Student > actual = studentService.getAllStudents( start, limit );

		assertEquals( expected, actual );
	}

	@Test
	void testGetStudentByIdShouldReturnStudent() {
		int id = 1;
		Student expected = getStudent( id );
		studentDao.save( expected );
		Student actual = studentService.getStudentById( id );

		assertEquals( expected, actual );
	}

	@Test
	void testGetStudentByIdShouldThrowNotFound() {
		assertThrows( EntityNotFoundException.class, () -> studentService.getStudentById( 1 ) );
	}

	@Test
	void testAddStudentShouldReturnId() {
		int id = 1;
		assertEquals( id, studentService.addStudent( getStudent( id ) ) );
	}

	@Test
	void testAddStudentWithCourseShouldReturnId() {
		int studentId = 1;
		int courseId = 1;
		assertEquals( studentId, studentService.addStudent( getStudentWithCourse( studentId, courseId ) ) );
	}

	@Test
	void testAddStudentWithCourseWithProfessorShouldReturnId() {
		int studentId = 1;
		int courseId = 1;
		int professorId = 1;
		assertEquals( studentId,
		              studentService.addStudent( getStudentWithCourseWithProfessor( studentId,
		                                                                            courseId,
		                                                                            professorId ) ) );
	}

	@Test
	void testUpdateStudentShouldReturnId() {
		int studentId = 1;
		Student student = getStudent( studentId );
		Student updatedStudent = getStudent(studentId);
		updatedStudent.setName( "updatedName" );
		updatedStudent.setEmail( "updatedEmail" );
		updatedStudent.setPhone( "updatedPhone" );
		updatedStudent.setAddress( "updatedAddress" );
		updatedStudent.setCreditBook( 12 );

		studentDao.save( student );
		studentService.updateStudent( studentId, updatedStudent );

		assertEquals( updatedStudent, studentDao.findById( studentId ).orElseThrow() );
	}

	@Test
	void testUpdateStudentShouldThrowEntityNotFoundException() {
		int studentId = 1;
		int courseId = 1;
		Student student = getStudent( studentId );
		Student studentWithCourse = getStudentWithCourse( studentId, courseId );

		studentDao.save( student );
		assertThrows( EntityNotFoundException.class, () -> studentService.updateStudent( 2, studentWithCourse ) );
	}


	@Test
	void testDeleteStudentByIdShouldReturnVoid() {
		studentDao.saveAll( List.of( getStudent( 1 ), getStudent( 2 ) ) );
		studentService.deleteStudentById( 1 );
		assertEquals( 1, studentDao.count() );
	}

	@Test
	void testDeleteStudentByIdShouldThrowEntityNotFoundException() {
		assertThrows( EntityNotFoundException.class, () -> studentService.deleteStudentById( 1 ) );
	}

	@Test
	void testDeleteAllStudentsShouldReturnVoid() {
		studentDao.saveAll( List.of( getStudent( 1 ), getStudent( 2 ) ) );
		studentService.deleteAllStudents();
		assertEquals( 0, studentDao.count() );
	}
}



