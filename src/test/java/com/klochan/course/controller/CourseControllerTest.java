package com.klochan.course.controller;

import com.klochan.course.controller.advice.ExceptionHandlers;
import com.klochan.course.dao.CourseDao;
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
public class CourseControllerTest {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private EntityManager entityManager;
	@Autowired
	private CourseController courseController;
	private MockMvc mockMVC;

	@BeforeEach
	void resetIds() {
		mockMVC = standaloneSetup( courseController ).addFilter( new CharacterEncodingFilter() )
		                                             .setControllerAdvice( new ExceptionHandlers() )
		                                             .build();
		entityManager.createNativeQuery( "ALTER TABLE Student AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE Course AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE Professor AUTO_INCREMENT = 1" ).executeUpdate();
		entityManager.createNativeQuery( "ALTER TABLE CourseStatus AUTO_INCREMENT = 1" ).executeUpdate();
	}

	@Test
	public void testGetAllCoursesShouldReturnEmptyList() throws Exception {
		mockMVC.perform( get( "/courses" ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 0 ) );
	}

	@Test
	void testGetCoursesShouldReturnListSize1() throws Exception {
		courseDao.save( getCourse( 1 ) );
		mockMVC.perform( get( "/courses" ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 1 ) );
	}

	@Test
	void testGetCoursesShouldReturnListSize2() throws Exception {
		courseDao.saveAll( List.of( getCourse( 1 ), getCourse( 2 ) ) );
		mockMVC.perform( get( "/courses" ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 2 ) );
	}

	@Test
	void testGetCoursesShouldReturnJson() throws Exception {
		int id = 1;
		courseDao.save( getCourse( id ) );

		mockMVC.perform( get( "/courses" ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( content().json( "[" + getCourseJson( id ) + "]" ) );

	}

	@Test
	void testGetCoursesWithLimitShouldReturnEmptyList() throws Exception {
		int start = 1;
		int limit = 1;

		mockMVC.perform( get( "/courses" ).param( "start", String.valueOf( start ) )
		                                  .param( "limit", String.valueOf( limit ) )
		                                  .accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 0 ) );
	}

	@Test
	void testGetCoursesWithWrongLimitShouldReturnEmptyList() throws Exception {
		int start = 10;
		int limit = 1;
		courseDao.save( getCourse( 1 ) );

		mockMVC.perform( get( "/courses" ).param( "start", String.valueOf( start ) )
		                                  .param( "limit", String.valueOf( limit ) )
		                                  .accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 0 ) );
	}

	@Test
	void testGetCoursesWithLimitShouldListSize2() throws Exception {
		int start = 0;
		int limit = 2;
		courseDao.saveAll( List.of( getCourse( 1 ), getCourse( 2 ) ) );

		mockMVC.perform( get( "/courses" ).param( "start", String.valueOf( start ) )
		                                  .param( "limit", String.valueOf( limit ) )
		                                  .accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 2 ) );
	}


	@Test
	void testGetCoursesWithWrongLimitShouldListSize2() throws Exception {
		int start = 0;
		int limit = 5;
		courseDao.saveAll( List.of( getCourse( 1 ), getCourse( 2 ) ) );

		mockMVC.perform( get( "/courses" ).param( "start", String.valueOf( start ) )
		                                  .param( "limit", String.valueOf( limit ) )
		                                  .accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( jsonPath( "$.length()" ).value( 2 ) );
	}


	@Test
	void testGetCourseByIdShouldReturnJson() throws Exception {
		int courseId = 1;
		courseDao.save( getCourse( courseId ) );
		String json = getCourseJson( courseId );

		mockMVC.perform( get( "/courses/" + courseId ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isOk() )
		       .andExpect( content().contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( content().json( json ) );
	}

	@Test
	void testGetCourseByIdShouldReturnNotFound() throws Exception {
		int courseId = 1;

		mockMVC.perform( get( "/courses/" + courseId ).accept( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isNotFound() );
	}

	@Test
	void testAddCourseShouldReturnCourseId() throws Exception {
		int courseId = 1;
		String json = getCourseJson( 1 );

		mockMVC.perform( post( "/courses/" ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isCreated() );
		assertEquals( 1, courseDao.count() );
	}

	@Test
	void testAddCourseWithProfessorShouldReturnCourseId() throws Exception {
		int courseId = 1;
		int professorId = 1;
		String json = getCourseWithProfessorJson( courseId, professorId );

		mockMVC.perform( post( "/courses/" ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isCreated() );
		assertEquals( 1, courseDao.count() );
	}

	@Test
	void testAddCourseWithValidationExceptionShouldReturnBadRequest() throws Exception {
		String json = getCourseJsonWithValidationErrors();

		mockMVC.perform( post( "/courses/" ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isBadRequest() );
		assertEquals( 0, courseDao.count() );
	}

	@Test
	void testAddCourseWithIncorrectJsonShouldReturnBadRequest() throws Exception {
		String json = getIncorrectJson();

		mockMVC.perform( post( "/courses/" ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isBadRequest() );
		assertEquals( 0, courseDao.count() );
	}

	@Test
	void testUpdateCourseShouldReturnNotFound() throws Exception {
		int courseId = 1;
		String json = getCourseJson( courseId );

		mockMVC.perform( put( "/course/" + courseId ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isNotFound() );
		assertEquals( 0, courseDao.count() );
	}

	@Test
	void testUpdateCourseWithCorrectJsonShouldReturnOk() throws Exception {
		int courseId = 1;
		int professorId = 1;
		courseDao.save( getCourse( courseId ) );
		String json = getCourseWithProfessorJson( courseId, professorId );

		mockMVC.perform( put( "/courses/" + courseId ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isOk() );
		assertEquals( 1, courseDao.count() );
	}

	@Test
	void testUpdateCourseWithValidationErrorsShouldReturnBadRequest() throws Exception {
		int courseId = 1;
		courseDao.save( getCourse( courseId ) );
		String json = getCourseJsonWithValidationErrors();

		mockMVC.perform( put( "/courses/" + courseId ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isBadRequest() );
		assertEquals( 1, courseDao.count() );
	}


	@Test
	void testUpdateCourseWithIncorrectJsonShouldReturnBadRequest() throws Exception {
		int courseId = 1;
		courseDao.save( getCourse( courseId ) );
		String json = getIncorrectJson();

		mockMVC.perform( put( "/courses/" + courseId ).contentType( APPLICATION_JSON_UTF8 ).content( json ) )
		       .andExpect( status().isBadRequest() );
		assertEquals( 1, courseDao.count() );
	}

	@Test
	void testDeleteCourseShouldReturnNoContent() throws Exception {
		int courseId = 1;
		courseDao.save( getCourse( courseId ) );

		mockMVC.perform( delete( "/courses/" + courseId ).contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isNoContent() );
		assertEquals( 0, courseDao.count() );
	}

	@Test
	void testDeleteCourseShouldReturnNotFound() throws Exception {
		int courseId = 1;
		mockMVC.perform( delete( "/courses/" + courseId ).contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isNotFound() );
		assertEquals( 0, courseDao.count() );
	}

	@Test
	void testDeleteAllCoursesShouldReturnNoContent() throws Exception {
		courseDao.saveAll( List.of( getCourse( 1 ), getCourse( 2 ) ) );
		mockMVC.perform( delete( "/courses/" ).contentType( APPLICATION_JSON_UTF8 ) )
		       .andExpect( status().isNoContent() );
		assertEquals( 0, courseDao.count() );
	}
}
