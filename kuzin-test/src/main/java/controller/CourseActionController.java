package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.Course;
import model.CourseFlow;
import model.Student;
import service.JSONService;
import service.MainListsHolder;

@Controller
public class CourseActionController {

	@Autowired
	ServletContext context;

	@RequestMapping(value = "/course", method = RequestMethod.GET)
	public String openStudentActionPage(@RequestParam(value = "t") int actionType,
			@RequestParam(value = "id") int courseNum, Model model) {

		Map<Integer, Course> courses = MainListsHolder.getCourses(context);

		if (courses.containsKey(courseNum) || actionType == MainController.ADD_ACTION_TYPE) {

			switch (actionType) {

			case MainController.DELETE_ACTION_TYPE:

				courses.remove(courseNum);

				JSONService.writeCourses(courses.values(), context);

				return "redirect:/courses";
			case MainController.EDIT_ACTION_TYPE:

				Course course = courses.get(courseNum);
				Map<Integer, Student> students = MainListsHolder.getStudents(context);
				List<Student> courseStudents = new ArrayList<>();


				for (Integer sId : course.getStudents()) {
					System.out.println(sId);
					if (students.containsKey(sId)) {
						courseStudents.add(students.get(sId));
					}
				}

				model.addAttribute("course", course);
				model.addAttribute("courseStudents", courseStudents);
				model.addAttribute("students", students.values());

				return "course";

			case MainController.ADD_ACTION_TYPE:

				model.addAttribute("course", new Course());

				return "course";
			}
		}

		return "courses";
	}

	@PostMapping("/course")
	public String saveStudentData(@Valid Course course, BindingResult bindingResult) {

		Map<Integer, Course> courses = MainListsHolder.getCourses(context);
		Course c = new Course();
		
		if (courses.containsKey(course.getNumber())) {
			c = courses.get(course.getNumber());			
		}else{
			c = new Course();
			c.setNumber(course.getNumber());
		}
		
		c.setName(course.getName());
		c.setPrice(course.getPrice());
		c.setProfessor(course.getProfessor());

		JSONService.writeCourses(courses.values(), context);

		return "redirect:/courses";

	}

	@RequestMapping(value = "/update_course", method = RequestMethod.GET)
	public String onStudentActionPage(@RequestParam(value = "a") int actionType,
			@RequestParam(value = "s_id") int scoreBookNum, @RequestParam(value = "c_id") int courseNum, Model model) {

		Map<Integer, Course> courses = MainListsHolder.getCourses(context);
		Map<Integer, Student> students = MainListsHolder.getStudents(context);
		Map<String, CourseFlow> courseFlows = MainListsHolder.getCourseFlow(context);

		if (students.containsKey(scoreBookNum)) {
			Student student = students.get(scoreBookNum);

			if (actionType == 1) {
				boolean cont = courses.get(courseNum).getStudents().contains(student.getScoreBookNum());
				if (!cont) {
					CourseFlow courseFlow = new CourseFlow();
					courseFlow.setCourse(courses.get(courseNum));
					courseFlow.generateId();
					courseFlow.output();					

					courseFlows.put(courseFlow.getCourseFlowId(), courseFlow);
					student.addCourseFlow(courseFlow);

					courses.get(courseNum).addStudent(student.getScoreBookNum());
				}else{
					return "redirect:/course?t=1&id=".concat(String.valueOf(courseNum));
				}
			} else if (actionType == 2) {
				int indexToRemove = -1;
				String courseFlowId = "";
				for (CourseFlow cf : student.getCourseFlow()) {
					CourseFlow realCf = courseFlows.get(cf.getCourseFlowId());
					if (realCf != null) {
						if (realCf.getCourse().getNumber() == courseNum) {
							indexToRemove = student.getCourseFlow().indexOf(cf);
							courseFlowId = cf.getCourseFlowId();
						}
					}
				}
				if (indexToRemove != -1) {
					courseFlows.remove(courseFlowId);
					student.getCourseFlow().remove(indexToRemove);
					courses.get(courseNum).removeStudent(student.getScoreBookNum());
				}
			}

		}

		List<CourseFlow> values = new ArrayList<>(courseFlows.values());
		
		JSONService.writeCourses(courses.values(), context);
		JSONService.writeCourseFlow(values, context);
		JSONService.writeStudents(students.values(), context);

		return "redirect:/course?t=1&id=".concat(String.valueOf(courseNum));
	}

}
