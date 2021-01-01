package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;

import model.Course;
import model.CourseFlow;
import model.Professor;
import model.Student;

public class MainListsHolder {

	private static Map<Integer,Student> students = new HashMap<>();
	private static Map<String,Professor> professors = new HashMap<>();
	private static Map<Integer,Course> courses = new HashMap<>();
	private static Map<String,CourseFlow> courseFlows = new HashMap<>();

	public static Map<String,Professor> getProfessors(ServletContext context) {

		if (professors == null || professors.isEmpty()) {
			professors = JSONService.readProfessors(context);
		}

		return professors;
	}
	
	public static Map<Integer,Student> getStudents(ServletContext context) {

		if (students == null || students.isEmpty()) {
			students = JSONService.readStudents(context);
		}

		return students;
	}
	
	public static Map<Integer,Course> getCourses(ServletContext context) {

		if (courses == null || courses.isEmpty()) {
			courses = JSONService.readCourses(context);
		}

		return courses;
	}
	
	public static Map<String,CourseFlow> getCourseFlow(ServletContext context) {

		if (courseFlows == null || courseFlows.isEmpty()) {
			courseFlows = JSONService.readCourseFlow(context);
		}

		return courseFlows;
	}

}
