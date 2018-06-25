package com.klochan.course;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klochan.course.model.Course;
import com.klochan.course.model.CourseStatus;
import com.klochan.course.model.Professor;
import com.klochan.course.model.Student;

import java.util.HashSet;

public class TestData {

	public static Student getStudent( int id ) {
		return Student.builder()
		              .studentId( id )
		              .name( "Ivan" )
		              .address( "Moscow" )
		              .phone( "123" )
		              .email( "some@email.com" )
		              .creditBook( 1 )
		              .courseStatusList( new HashSet<>() )
		              .build();
	}

	public static String getStudentJson( int id ) {
		return "{\n" + "  \"studentId\": " + id + ",\n" + "  \"name\": \"Ivan\",\n" + "  \"address\": \"Moscow\",\n" +
		       "  \"phone\": \"123\",\n" + "  \"email\": \"some@email.com\",\n" + "  \"creditBook\": 1,\n" +
		       "  \"courseStatusList\": []" + "\n" + "}";
	}


	public static Course getCourse( int id ) {
		return Course.builder()
		             .courseId( id )
		             .name( "Java" )
		             .number( 1 )
		             .cost( 123 )
		             .professors( new HashSet<>() )
		             .students( new HashSet<>() )
		             .build();
	}

	public static void main( String[] args ) throws JsonProcessingException {
		System.out.println( new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString( getCourse( 1 ) ) );
	}

	public static String getCourseJson( int id ) {
		return "{\n" + "  \"courseId\": " + id + ",\n" + "  \"name\": \"Java\",\n" + "  \"number\": 1,\n" +
		       "  \"cost\": 123.0,\n" + "  \"students\": [],\n" + "  \"professors\": []\n" + "}";
	}

	public static Professor getProfessor( int id ) {
		return Professor.builder()
		                .professorId( id )
		                .name( "Vova" )
		                .phone( "123" )
		                .address( "Kiev" )
		                .salary( 123.0f )
		                .courses( new HashSet<>() )
		                .build();
	}

	public static String getProfessorJson( int id ) {
		return "{\n" + "  \"professorId\": " + id + ",\n" + "  \"name\": \"Vova\",\n" + "  \"address\": \"Kiev\",\n" +
		       "  \"phone\": \"123\",\n" + "  \"salary\": 123.0,\n" + "  \"courses\": []\n" + "}";
	}

	public static String getStudentJsonWithValidationErrors() {
		return "{\n" + "  \"studentId\": -1,\n" + "  \"name\": null,\n" + "  \"address\": null,\n" +
		       "  \"phone\": null,\n" + "  \"email\": \"some@emai@l.com\",\n" + "  \"creditBook\": -11,\n" +
		       "  \"courseStatusList\": []\n" + "}";
	}

	public static String getCourseJsonWithValidationErrors() {
		return "{\n" + "  \"courseId\": -1,\n" + "  \"name\": null,\n" + "  \"number\": -1,\n" + "  \"cost\": -123," +
		       "\n" + "  \"students\": [],\n" + "  \"professors\": []\n" + "}";
	}

	public static String getProfessorJsonWithValidationErrors() {
		return "{\n" + "  \"professorId\": -1,\n" + "  \"name\": null,\n" + "  \"address\": null,\n" +
		       "  \"salary\": -123.0,\n" + "  \"courses\": []\n" + "}";
	}

	public static String getIncorrectJson() {
		return "{\n" + "  \"test\": 1\n" + "}";
	}

	public static Student getStudentWithCourse( int studentId, int courseId ) {
		Student student = getStudent( studentId );
		Course course = getCourse( courseId );
		course.addStudent( student );
		CourseStatus status = new CourseStatus( course );
		status.setCourseStatusId( 1 );
		student.getCourseStatusList().add( status );
		return student;
	}

	public static String getStudentWithCourseJson( int studentId, int courseId ) {
		return "{\n" + "  \"studentId\" : " + studentId + ",\n" + "  \"name\" : \"Ivan\",\n" +
		       "  \"address\" : \"Moscow\",\n" + "  \"phone\" : \"123\",\n" + "  \"email\" : \"some@email.com\",\n" +
		       "  \"creditBook\" : 1,\n" + "  \"courseStatusList\" : [ {\n" + "    \"courseStatusId\" : 1,\n" +
		       "    \"completed\" : false,\n" + "    \"scores\" : [ ],\n" + "    \"finalScore\" : 0,\n" +
		       "    \"course\" : {\n" + "      \"courseId\" : " + courseId + ",\n" + "      \"name\" : \"Java\",\n" +
		       "      \"number\" : 1,\n" + "      \"cost\" : 123.0,\n" + "      \"students\" : [ 1 ],\n" +
		       "      \"professors\" : [ ]\n" + "    }\n" + "  } ]\n" + "}";
	}

	public static Course getCourseWithProfessor( int courseId, int professorId ) {
		Course course = getCourse( courseId );
		Professor professor = getProfessor( professorId );
		course.addProfessor( professor );
		professor.addCourse( course );
		return course;
	}

	public static String getCourseWithProfessorJson( int courseId, int professorId ) {
		return "{\n" + "  \"courseId\" : " + courseId + ",\n" + "  \"name\" : \"Java\",\n" + "  \"number\" : 1,\n" +
		       "  \"cost\" : 123.0,\n" + "  \"students\" : [ ],\n" + "  \"professors\" : [ {\n" +
		       "    \"professorId\" : " + professorId + ",\n" + "    \"name\" : \"Vova\",\n" +
		       "    \"address\" : \"Kiev\",\n" + "    \"phone\" : \"123\",\n" + "    \"salary\" : 123.0,\n" +
		       "    \"courses\" : [ 1 ]\n" + "  } ]\n" + "}";
	}

	public static Professor getProfessorWithCourse( int professorId, int courseId ) {
		Professor professor = getProfessor( professorId );
		Course course = getCourse( courseId );
		course.addProfessor( professor );
		professor.addCourse( course );
		return professor;
	}

	public static String getProfessorWithCourseJson( int professorId, int courseId ) {
		return "{\n" + "  \"professorId\" : " + professorId + ",\n" + "  \"name\" : \"Vova\",\n" +
		       "  \"address\" : \"Kiev\",\n" + "  \"phone\" : \"123\",\n" + "  \"salary\" : 123.0,\n" +
		       "  \"courses\" : [ {\n" + "    \"courseId\" : " + courseId + ",\n" + "    \"name\" : \"Java\",\n" +
		       "    \"number\" : 1,\n" + "    \"cost\" : 123.0,\n" + "    \"students\" : [ ],\n" +
		       "    \"professors\" : [ 1 ]\n" + "  } ]\n" + "}";
	}

	public static Student getStudentWithCourseWithProfessor( int studentId, int professorId, int courseId ) {
		Student student = getStudent( studentId );
		Course course = getCourse( courseId );
		course.addStudent( student );
		CourseStatus status = new CourseStatus( course );
		status.setCourseStatusId( 1 );
		student.getCourseStatusList().add( status );
		Professor professor = getProfessor( professorId );
		course.addProfessor( professor );
		professor.addCourse( course );
		return student;
	}

	public static String getStudentWithCourseWithProfessorJson( int studentId, int professorId, int courseId ) {
		return "{\n" + "  \"studentId\" : " + studentId + ",\n" + "  \"name\" : \"Ivan\",\n" +
		       "  \"address\" : \"Moscow\",\n" + "  \"phone\" : \"123\",\n" + "  \"email\" : \"some@email.com\",\n" +
		       "  \"creditBook\" : 1,\n" + "  \"courseStatusList\" : [ {\n" + "    \"courseStatusId\" : 1,\n" +
		       "    \"completed\" : false,\n" + "    \"scores\" : [ ],\n" + "    \"finalScore\" : 0,\n" +
		       "    \"course\" : {\n" + "      \"courseId\" : " + courseId + ",\n" + "      \"name\" : \"Java\",\n" +
		       "      \"number\" : 1,\n" + "      \"cost\" : 123.0,\n" + "      \"students\" : [ 1 ],\n" +
		       "      \"professors\" : [ {\n" + "        \"professorId\" : " + professorId + ",\n" +
		       "        \"name\" : \"Vova\",\n" + "        \"address\" : \"Kiev\",\n" +
		       "        \"phone\" : \"123\",\n" + "        \"salary\" : 123.0,\n" + "        \"courses\" : [ 1 ]\n" +
		       "      } ]\n" + "    }\n" + "  } ]\n" + "}";
	}

	public static String getEnrollJson( int courseId ) {
		return "{\n" + "\"courseId\": " + courseId + "\n" + "}";
	}

	public static String getCompleteJson( int studentId ) {
		return "{\n" + "\"studentId\": " + studentId + "\n" + "}";
	}

	public static String getScoreJson( int courseId, int score ) {
		return "{\n" + "  \"studentId\": " + courseId + ",\n" + "  \"score\": " + score + "\n" + "}";
	}

	public static String getCourseToProfessorJson( int courseId ) {
		return "{\n" + "\"courseId\": " + courseId + "\n" + "}";
	}
}
