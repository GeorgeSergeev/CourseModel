package com.klochan.course.controller;

import com.klochan.course.controller.advice.ExceptionHandlers;
import com.klochan.course.dao.StudentDao;
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
class StudentControllerTest {

	@Autowired
	private StudentDao studentDao;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private StudentController studentController;
	private MockMvc mockMVC;

	@BeforeEach
	void resetIds() {
		mockMVC = standaloneSetup( studentController ).addFilter( new CharacterEncodingFilter() )
		                                              .setControllerAdvice( new ExceptionHandlers() )
		                                              .build();
		entityManager.createNativeQuery( "ALTER TABLE Course AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE Professor AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE Student AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE CourseStatus AUTO_INCREMENT = 1" ).executeUpdate();
	}

	@Test
	public void testGetAllStudentsShouldReturnEmptyList() throws Exception {
		mockMVC.perform( get( "/students" ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 0 ) );
	}

	@Test
	void testGetStudentsShouldReturnListSize1() throws Exception {
		studentDao.save( getStudent( 1 ) );
		mockMVC.perform( get( "/students" ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 1 ) );
	}


	@Test
	void testGetStudentsShouldReturnListSize2() throws Exception {
		studentDao.saveAll( List.of( getStudent( 1 ), getStudent( 2 ) ) );
		mockMVC.perform( get( "/students" ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 2 ) );
	}

	@Test
	void testGetStudentsShouldReturnJson() throws Exception {
		int id = 1;
		studentDao.save( getStudent( id ) );

		mockMVC.perform( get( "/students" ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( content().json( "[" + getStudentJson( id ) + "]" ) );

	}

	@Test
	void testGetStudentsWithLimitShouldReturnEmptyList() throws Exception {
		int start = 1;
		int limit = 1;

		mockMVC.perform( get( "/students" ).param( "start", String.valueOf( start ) )
		                                   .param( "limit", String.valueOf( limit ) )
		                                   .accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 0 ) );
	}

	@Test
	void testGetStudentsWithWrongLimitShouldReturnEmptyList() throws Exception {
		int start = 10;
		int limit = 1;
		studentDao.save( getStudent( 1 ) );

		mockMVC.perform( get( "/students" ).param( "start", String.valueOf( start ) )
		                                   .param( "limit", String.valueOf( limit ) )
		                                   .accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 0 ) );
	}

	@Test
	void testGetStudentsWithLimitShouldListSize2() throws Exception {
		int start = 0;
		int limit = 2;
		studentDao.saveAll( List.of( getStudent( 1 ), getStudent( 2 ) ) );

		mockMVC.perform( get( "/students" ).param( "start", String.valueOf( start ) )
		                                   .param( "limit", String.valueOf( limit ) )
		                                   .accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 2 ) );
	}


	@Test
	void testGetStudentsWithWrongLimitShouldListSize2() throws Exception {
		int start = 0;
		int limit = 5;
		studentDao.saveAll( List.of( getStudent( 1 ), getStudent( 2 ) ) );

		mockMVC.perform( get( "/students" ).param( "start", String.valueOf( start ) )
		                                   .param( "limit", String.valueOf( limit ) )
		                                   .accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 2 ) );
	}


	@Test
	void testGetStudentByIdShouldReturnJson() throws Exception {
		int studentId = 1;
		studentDao.save( getStudent( studentId ) );

		mockMVC.perform( get( "/students/" + studentId ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( content().json( getStudentJson( studentId ) ) );
	}

	@Test
	void testGetStudentByIdShouldReturnNotFound() throws Exception {
		int studentId = 1;

		mockMVC.perform( get( "/students/" + studentId ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isNotFound() );
	}

	@Test
	void testAddStudentShouldReturnStudentId() throws Exception {
		int studentId = 1;
		String json = getStudentJson( 1 );

		mockMVC.perform( post( "/students/" ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isCreated() );
		assertEquals( 1, studentDao.count() );
	}

	@Test
	void testAddStudentWithCourseShouldReturnStudentId() throws Exception {
		int studentId = 1;
		int courseId = 1;
		String json = getStudentWithCourseJson( studentId, courseId );

		mockMVC.perform( post( "/students/" ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isCreated() );

		assertEquals( 1, studentDao.count() );
	}

	@Test
	void testAddStudentWithCourseWithProfessorShouldReturnStudentId() throws Exception {
		int studentId = 1;
		int courseId = 1;
		int professorId = 1;
		String json = getStudentWithCourseWithProfessorJson( studentId, professorId, courseId );

		mockMVC.perform( post( "/students/" ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isCreated() );
		assertEquals( 1, studentDao.count() );
	}

	@Test
	void testAddStudentWithValidationExceptionShouldReturnBadRequest() throws Exception {
		String json = getStudentJsonWithValidationErrors();

		mockMVC.perform( post( "/students/" ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isBadRequest() );
		assertEquals( 0, studentDao.count() );
	}

	@Test
	void testAddStudentWithIncorrectJsonShouldReturnBadRequest() throws Exception {
		String json = getIncorrectJson();

		mockMVC.perform( post( "/students/" ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isBadRequest() );
		assertEquals( 0, studentDao.count() );
	}

	@Test
	void testUpdateStudentShouldReturnNotFound() throws Exception {
		int studentId = 1;
		String json = getStudentJson( studentId );

		mockMVC.perform( put( "/student/" + studentId ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isNotFound() );
		assertEquals( 0, studentDao.count() );
	}

	@Test
	void testUpdateStudentWithCorrectJsonShouldReturnOk() throws Exception {
		int studentId = 1;
		int courseId = 1;
		studentDao.save( getStudent( studentId ) );
		String json = getStudentWithCourseJson( studentId, courseId );

		mockMVC.perform( put( "/students/" + studentId ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isOk() );
		assertEquals( 1, studentDao.count() );
	}

	@Test
	void testUpdateStudentWithValidationErrorsShouldReturnBadRequest() throws Exception {
		int studentId = 1;
		studentDao.save( getStudent( studentId ) );
		String json = getStudentJsonWithValidationErrors();

		mockMVC.perform( put( "/students/" + studentId ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isBadRequest() );
		assertEquals( 1, studentDao.count() );
	}

	@Test
	void testUpdateStudentWitIncorrectJsonShouldReturnBadRequest() throws Exception {
		int studentId = 1;
		studentDao.save( getStudent( studentId ) );
		String json = getIncorrectJson();

		mockMVC.perform( put( "/students/" + studentId ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isBadRequest() );
		assertEquals( 1, studentDao.count() );
	}

	@Test
	void testDeleteStudentShouldReturnNoContent() throws Exception {
		int studentId = 1;
		studentDao.save( getStudent( studentId ) );

		mockMVC.perform( delete( "/students/" + studentId ).contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isNoContent() );
		assertEquals( 0, studentDao.count() );
	}

	@Test
	void testDeleteStudentShouldReturnNotFound() throws Exception {
		int studentId = 1;
		mockMVC.perform( delete( "/students/" + studentId ).contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isNotFound() );
		assertEquals( 0, studentDao.count() );
	}

	@Test
	void testDeleteAllStudentsShouldReturnNoContent() throws Exception {
		studentDao.saveAll( List.of( getStudent( 1 ), getStudent( 2 ) ) );
		mockMVC.perform( delete( "/students/" ).contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isNoContent() );
		assertEquals( 0, studentDao.count() );
	}
}
