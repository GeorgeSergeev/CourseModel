package com.klochan.course.controller;

import com.klochan.course.controller.advice.ExceptionHandlers;
import com.klochan.course.model.Course;
import com.klochan.course.model.Professor;
import com.klochan.course.model.Student;
import com.klochan.course.service.CourseService;
import com.klochan.course.service.ProfessorService;
import com.klochan.course.service.StudentService;
import com.klochan.course.service.UniversityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.persistence.EntityNotFoundException;

import static com.klochan.course.TestData.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class UniversityConrtrollerTest {

	@Mock
	private UniversityService universityService;
	@Mock
	private StudentService studentService;
	@Mock
	private CourseService courseService;
	@Mock
	private ProfessorService professorService;
	@InjectMocks
	private UniversityController universityController;
	private MockMvc mockMVC;

	@BeforeEach
	void prepareMock() {
		initMocks( this );
		mockMVC = standaloneSetup( universityController ).addFilter( new CharacterEncodingFilter() )
		                                                 .setControllerAdvice( new ExceptionHandlers() )
		                                                 .build();
	}

	@Test
	void testEnrollShouldBeOk() throws Exception {
		int studentId = 1;
		int courseId = 1;
		Student student = getStudent( studentId );
		Course course = getCourse( courseId );
		when( studentService.getStudentById( anyInt() ) ).thenReturn( student );
		when( courseService.getCourseById( anyInt() ) ).thenReturn( course );

		mockMVC.perform( post( "/students/{studentId}/courses", studentId ).param( "courseId", "" + courseId ) )
		       .andExpect( status().isOk() );

		verify( universityService ).enrollStudentToCourse( student, course );
	}

	@Test
	void testEnrollShouldReturnNotFound() throws Exception {
		int studentId = 1;
		int courseId = 1;
		when( studentService.getStudentById( anyInt() ) ).thenThrow( EntityNotFoundException.class );

		mockMVC.perform( post( "/students/{studentId}/courses", studentId ).param( "courseId", "" + courseId ) )
		       .andExpect( status().isNotFound() );
		verifyZeroInteractions( universityService );
	}

	@Test
	void testCompleteShouldBeOk() throws Exception {
		int studentId = 1;
		int courseId = 1;
		Student student = getStudent( studentId );
		Course course = getCourse( courseId );
		when( studentService.getStudentById( anyInt() ) ).thenReturn( student );
		when( courseService.getCourseById( anyInt() ) ).thenReturn( course );

		mockMVC.perform( post( "/courses/{courseId}/", courseId ).param( "studentId", "" + studentId ) )
		       .andExpect( status().isOk() );
		verify( universityService ).completeCourse( student, course );
	}

	@Test
	void testCompleteShouldReturnNotFound() throws Exception {
		int studentId = 1;
		int courseId = 1;
		when( studentService.getStudentById( anyInt() ) ).thenThrow( EntityNotFoundException.class );

		mockMVC.perform( post( "/courses/{courseId}/", courseId ).param( "studentId", "" + studentId ) )
		       .andExpect( status().isNotFound() );
		verifyZeroInteractions( universityService );
	}


	@Test
	void testAddScoreShouldBeOk() throws Exception {
		int studentId = 1;
		int courseId = 1;
		int score = 100;
		Student student = getStudentWithCourse( studentId, courseId );
		Course course = student.getActiveCourses().get( 0 );
		when( studentService.getStudentById( anyInt() ) ).thenReturn( student );
		when( courseService.getCourseById( anyInt() ) ).thenReturn( course );

		mockMVC.perform( post( "/students/{studentId}/scores", studentId ).param( "score", "" + score )
		                                                                  .param( "courseId", "" + courseId ) )
		       .andExpect( status().isOk() );
		verify( universityService ).addScore( student, course, score );
	}

	@Test
	void testAddScoreShouldReturnNotFound() throws Exception {
		int studentId = 1;
		int courseId = 1;
		int score = 100;
		when( studentService.getStudentById( anyInt() ) ).thenThrow( EntityNotFoundException.class );

		mockMVC.perform( post( "/students/{studentId}/scores", studentId ).param( "score", "" + score )
		                                                                  .param( "courseId", "" + courseId ) )
		       .andExpect( status().isNotFound() );
	}

	@Test
	void testAddCourseToProfessorShouldBeOk() throws Exception {
		int courseId = 1;
		int professorId = 1;
		Course course = getCourseWithProfessor( courseId, professorId );
		Professor professor = (Professor) course.getProfessors().toArray()[ 0 ];
		when( courseService.getCourseById( anyInt() ) ).thenReturn( course );
		when( professorService.getProfessorById( anyInt() ) ).thenReturn( professor );

		mockMVC.perform( post( "/professors/{professorId}/courses", professorId ).param( "courseId", "" + courseId ) )
		       .andExpect( status().isOk() );
		verify( universityService ).addCourseToProfessor( course, professor );
	}

	@Test
	void testAddCourseToProfessorShouldReturnNotFound() throws Exception {
		int courseId = 1;
		int professorId = 1;
		when( professorService.getProfessorById( anyInt() ) ).thenThrow( EntityNotFoundException.class );

		mockMVC.perform( post( "/professors/{professorId}/courses", professorId ).param( "courseId", "" + courseId ) )
		       .andExpect( status().isNotFound() );
		verifyZeroInteractions( universityService );
	}


}
