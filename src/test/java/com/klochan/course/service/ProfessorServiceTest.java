package com.klochan.course.service;

import com.klochan.course.dao.ProfessorDao;
import com.klochan.course.model.Professor;
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

import static com.klochan.course.TestData.getProfessor;
import static com.klochan.course.TestData.getProfessorWithCourse;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith( SpringExtension.class )
@SpringBootTest
@Transactional
class ProfessorServiceTest {

	@Autowired
	private ProfessorService professorService;
	@Autowired
	private EntityManager entityManager;
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
	void testGetAllProfessorsShouldReturnEmptyList() {
		assertEquals( List.of(), professorService.getAllProfessors() );

		professorDao.saveAll( List.of( getProfessor( 1 ), getProfessor( 2 ), getProfessor( 3 ) ) );
		professorDao.findAll().forEach( s -> System.out.println( s.getProfessorId() ) );
	}

	@Test
	void testGetAllProfessorsShouldReturnListSize2() {
		List< Professor > expected = List.of( getProfessor( 1 ), getProfessor( 2 ) );
		professorDao.saveAll( expected );

		List< Professor > actual = professorService.getAllProfessors();

		assertIterableEquals( expected, actual );
	}


	@Test
	void testGetAllProfessorsWithLimitShouldReturnEmptyList() {
		int start = 1;
		int limit = 2;

		List< Professor > actual = professorService.getAllProfessors( start, limit );

		assertEquals( List.< Professor >of(), actual );
	}

	@Test
	void testGetAllProfessorsWithLimitShouldReturnListSize2() {
		int start = 0;
		int limit = 2;
		List< Professor > expected = List.of( getProfessor( 1 ), getProfessor( 2 ) );
		professorDao.saveAll( expected );

		List< Professor > actual = professorService.getAllProfessors( start, limit );

		assertEquals( expected, actual );
	}

	@Test
	void testGetProfessorByIdShouldReturnProfessor() {
		int id = 1;
		Professor expected = getProfessor( id );
		professorDao.save( expected );
		Professor actual = professorService.getProfessorById( id );

		assertEquals( expected, actual );
	}

	@Test
	void testGetProfessorByIdShouldThrowNotFound() {
		assertThrows( EntityNotFoundException.class, () -> professorService.getProfessorById( 1 ) );
	}

	@Test
	void testAddProfessorShouldReturnId() {
		int id = 1;
		assertEquals( id, professorService.addProfessor( getProfessor( id ) ) );
	}

	@Test
	void testAddProfessorWithCourseShouldReturnId() {
		int professorId = 1;
		int courseId = 1;
		assertEquals( professorId, professorService.addProfessor( getProfessorWithCourse( professorId, courseId ) ) );
	}


	@Test
	void testUpdateProfessorShouldReturnId() {
		int professorId = 1;
		Professor professor = getProfessor( professorId );
		Professor updatedProfessor = getProfessor( professorId );
		updatedProfessor.setName( "updatedName" );
		updatedProfessor.setPhone( "updatedPhone" );
		updatedProfessor.setAddress( "updatedAddress" );
		updatedProfessor.setSalary( 12.0f );

		professorDao.save( professor );
		professorService.updateProfessor( professorId, updatedProfessor );

		assertEquals( updatedProfessor, professorDao.findById( professorId ).orElseThrow() );
	}

	@Test
	void testUpdateProfessorShouldThrowEntityNotFoundException() {
		int professorId = 1;
		int courseId = 1;
		Professor professor = getProfessor( professorId );
		Professor professorWithProfessor = getProfessorWithCourse( professorId, courseId );

		professorDao.save( professor );
		assertThrows( EntityNotFoundException.class,
		              () -> professorService.updateProfessor( 2, professorWithProfessor ) );
	}


	@Test
	void testDeleteProfessorByIdShouldReturnVoid() {
		professorDao.saveAll( List.of( getProfessor( 1 ), getProfessor( 2 ) ) );
		professorService.deleteProfessorById( 1 );
		assertEquals( 1, professorDao.count() );
	}

	@Test
	void testDeleteProfessorByIdShouldThrowEntityNotFoundException() {
		assertThrows( EntityNotFoundException.class, () -> professorService.deleteProfessorById( 1 ) );
	}

	@Test
	void testDeleteAllProfessorsShouldReturnVoid() {
		professorDao.saveAll( List.of( getProfessor( 1 ), getProfessor( 2 ) ) );
		professorService.deleteAllProfessors();
		assertEquals( 0, professorDao.count() );
	}
}



