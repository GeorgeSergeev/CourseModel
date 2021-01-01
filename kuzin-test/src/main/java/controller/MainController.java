package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import model.Course;
import model.CourseFlow;
import model.Professor;
import model.Student;
import service.JSONService;
import service.MainListsHolder;

@Controller
public class MainController {

	@Autowired
	ServletContext context;

	public static final int DELETE_ACTION_TYPE = 0;
	public static final int EDIT_ACTION_TYPE = 1;
	public static final int ADD_ACTION_TYPE = 2;
		

	@RequestMapping("/")
	public String greeting(Model model) {	
		
		return "index";
	}

	@RequestMapping("/professors")
	public String openProfessorsPage(Model model) {

		Map<String,Professor> professors = MainListsHolder.getProfessors(context);

		model.addAttribute("professors", professors.values());
		
		return "professorsListPage";
	}

	@RequestMapping("/students")
	public String openStudentsPage(Model model) {

		Map<Integer,Student> students = MainListsHolder.getStudents(context);

		model.addAttribute("students", students.values());

		return "studentsListPage";
	}	

	@RequestMapping("/courses")
	public String openSoursesPage(Model model) {
		
		Map<Integer,Course> courses = MainListsHolder.getCourses(context);

		model.addAttribute("courses", courses.values());
		
		return "courseListPage";
	}
}