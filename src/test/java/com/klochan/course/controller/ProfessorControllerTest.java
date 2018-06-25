package com.klochan.course.controller;

import com.klochan.course.controller.advice.ExceptionHandlers;
import com.klochan.course.dao.ProfessorDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

import static com.klochan.course.TestData.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith( SpringExtension.class )
@SpringBootTest
@Transactional
public class ProfessorControllerTest {

	@Autowired
	private ProfessorDao professorDao;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private ProfessorController professorController;
	private MockMvc mockMVC;

	@BeforeEach
	void resetIds() {
		mockMVC = standaloneSetup( professorController ).addFilter( new CharacterEncodingFilter() )
		                                                .setControllerAdvice( new ExceptionHandlers() )
		                                                .build();
		entityManager.createNativeQuery( "ALTER TABLE Course AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE Student AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE Professor AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE CourseStatus AUTO_INCREMENT = 1" ).executeUpdate();
	}

	@Test
	public void testGetAllProfessorsShouldReturnEmptyList() throws Exception {
		mockMVC.perform( get( "/professors" ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 0 ) );
	}

	@Test
	void testGetProfessorsShouldReturnListSize1() throws Exception {
		professorDao.save( getProfessor( 1 ) );
		mockMVC.perform( get( "/professors" ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 1 ) );
	}


	@Test
	void testGetProfessorsShouldReturnListSize2() throws Exception {
		professorDao.saveAll( List.of( getProfessor( 1 ), getProfessor( 2 ) ) );
		mockMVC.perform( get( "/professors" ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 2 ) );
	}

	@Test
	void testGetProfessorsShouldReturnJson() throws Exception {
		int id = 1;
		professorDao.save( getProfessor( id ) );

		mockMVC.perform( get( "/professors" ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( content().json( "[" + getProfessorJson( id ) + "]" ) );

	}

	@Test
	void testGetProfessorsWithLimitShouldReturnEmptyList() throws Exception {
		int start = 1;
		int limit = 1;

		mockMVC.perform( get( "/professors" ).param( "start", String.valueOf( start ) )
		                                     .param( "limit", String.valueOf( limit ) )
		                                     .accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 0 ) );
	}

	@Test
	void testGetProfessorsWithWrongLimitShouldReturnEmptyList() throws Exception {
		int start = 10;
		int limit = 1;
		professorDao.save( getProfessor( 1 ) );

		mockMVC.perform( get( "/professors" ).param( "start", String.valueOf( start ) )
		                                     .param( "limit", String.valueOf( limit ) )
		                                     .accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 0 ) );
	}

	@Test
	void testGetProfessorsWithLimitShouldListSize2() throws Exception {
		int start = 0;
		int limit = 2;
		professorDao.saveAll( List.of( getProfessor( 1 ), getProfessor( 2 ) ) );

		mockMVC.perform( get( "/professors" ).param( "start", String.valueOf( start ) )
		                                     .param( "limit", String.valueOf( limit ) )
		                                     .accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 2 ) );
	}


	@Test
	void testGetProfessorsWithWrongLimitShouldListSize2() throws Exception {
		int start = 0;
		int limit = 5;
		professorDao.saveAll( List.of( getProfessor( 1 ), getProfessor( 2 ) ) );

		mockMVC.perform( get( "/professors" ).param( "start", String.valueOf( start ) )
		                                     .param( "limit", String.valueOf( limit ) )
		                                     .accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 2 ) );
	}

	@Test
	void testGetProfessorByIdShouldReturnJson() throws Exception {
		int professorId = 1;
		professorDao.save( getProfessor( professorId ) );
		String json = getProfessorJson( professorId );

		mockMVC.perform( get( "/professors/" + professorId ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( content().json( json ) );
	}

	@Test
	void testGetProfessorByIdShouldReturnNotFound() throws Exception {
		int professorId = 1;

		mockMVC.perform( get( "/professors/" + professorId ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isNotFound() );
	}

	@Test
	void testAddProfessorShouldReturnProfessorId() throws Exception {
		int professorId = 1;
		String json = getProfessorJson( 1 );

		mockMVC.perform( post( "/professors/" ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isCreated() );
		assertEquals( 1, professorDao.count() );
	}

	@Test
	void testAddProfessorWithProfessorShouldReturnProfessorId() throws Exception {
		int professorId = 1;
		int courseId = 1;
		String json = getProfessorWithCourseJson( professorId, courseId );

		mockMVC.perform( post( "/professors/" ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isCreated() );
		assertEquals( 1, professorDao.count() );
	}

	@Test
	void testAddProfessorWithValidationExceptionShouldReturnBadRequest() throws Exception {
		String json = getProfessorJsonWithValidationErrors();

		mockMVC.perform( post( "/professors/" ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isBadRequest() );
		assertEquals( 0, professorDao.count() );
	}

	@Test
	void testUpdateProfessorShouldReturnNotFound() throws Exception {
		int professorId = 1;
		String json = getProfessorJson( professorId );

		mockMVC.perform( put( "/professor/" + professorId ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isNotFound() );

		assertEquals( 0, professorDao.count() );
	}

	@Test
	void testUpdateProfessorWithCorrectJsonShouldReturnOk() throws Exception {
		int professorId = 1;
		int courseId = 1;
		professorDao.save( getProfessor( professorId ) );
		String json = getProfessorWithCourseJson( professorId, courseId );

		mockMVC.perform( put( "/professors/" + professorId ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isOk() );

		assertEquals( 1, professorDao.count() );
	}

	@Test
	void testUpdateProfessorWithValidationErrorsShouldReturnBadRequest() throws Exception {
		int professorId = 1;
		professorDao.save( getProfessor( professorId ) );
		String json = getProfessorJsonWithValidationErrors();

		mockMVC.perform( put( "/professors/" + professorId ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isBadRequest() );

		assertEquals( 1, professorDao.count() );
	}

	@Test
	void testUpdateProfessorWithIncorrectJsonShouldReturnBadRequest() throws Exception {
		int professorId = 1;
		professorDao.save( getProfessor( professorId ) );
		String json = getIncorrectJson();

		mockMVC.perform( put( "/professors/" + professorId ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isBadRequest() );

		assertEquals( 1, professorDao.count() );
	}

	@Test
	void testDeleteProfessorShouldReturnNoContent() throws Exception {
		int professorId = 1;
		professorDao.save( getProfessor( professorId ) );

		mockMVC.perform( delete( "/professors/" + professorId ).contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isNoContent() );

		assertEquals( 0, professorDao.count() );
	}

	@Test
	void testDeleteProfessorShouldReturnNotFound() throws Exception {
		int professorId = 1;
		mockMVC.perform( delete( "/professors/" + professorId ).contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isNotFound() );
		assertEquals( 0, professorDao.count() );
	}

	@Test
	void testDeleteAllProfessorsShouldReturnNoContent() throws Exception {
		professorDao.saveAll( List.of( getProfessor( 1 ), getProfessor( 2 ) ) );
		mockMVC.perform( delete( "/professors/" ).contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isNoContent() );
		assertEquals( 0, professorDao.count() );
	}
}
