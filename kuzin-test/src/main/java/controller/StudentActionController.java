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
public class StudentActionController {

	@Autowired
	ServletContext context;

	@RequestMapping(value = "/student", method = RequestMethod.GET)
	public String openStudentActionPage(@RequestParam(value = "t") int actionType,
			@RequestParam(value = "id") int scoreBookNum, Model model) {

		Map<Integer, Student> students = MainListsHolder.getStudents(context);

		if (students.containsKey(scoreBookNum) || actionType == MainController.ADD_ACTION_TYPE) {

			switch (actionType) {
			case MainController.DELETE_ACTION_TYPE:

				students.remove(scoreBookNum);

				JSONService.writeStudents(students.values(), context);

				return "redirect:/students";
			case MainController.EDIT_ACTION_TYPE:

				Map<Integer, Course> courses = MainListsHolder.getCourses(context);
				Map<String, CourseFlow> courseFlows = MainListsHolder.getCourseFlow(context);

				Student student = students.get(scoreBookNum);
				List<CourseFlow> realCourseFlowList = new ArrayList();
				List<Course> outputCourseList = new ArrayList();

				if (!student.getCourseFlow().isEmpty()) {
					for (CourseFlow cf : student.getCourseFlow()) {
						if (cf.getCourse() == null) {
							CourseFlow tmp = courseFlows.get(cf.getCourseFlowId());
							realCourseFlowList.add(tmp);
						}
					}

					for (int i = 0; i < realCourseFlowList.size(); i++) {
						if (realCourseFlowList.get(i).getCourse() == null || realCourseFlowList.get(i).getCourse().getName() == null) {
							realCourseFlowList.get(i)
									.setCourse(courses.get(realCourseFlowList.get(i).getCourse().getNumber()));
							outputCourseList.add(courses.get(realCourseFlowList.get(i).getCourse().getNumber()));
						}
					}
				}

				model.addAttribute("student", student);
				model.addAttribute("midScore", student.getMidScore());
				model.addAttribute("canEnroll", student.canEnroll());
				model.addAttribute("courseList", outputCourseList);

				return "student";
			case MainController.ADD_ACTION_TYPE:
				model.addAttribute("student", new Student());

				return "student";

			}
		}

		return "students";
	}

	@PostMapping("/student")
	public String saveStudentData(@Valid Student student, BindingResult bindingResult) {

		Map<Integer, Student> students = MainListsHolder.getStudents(context);

		if (students.containsKey(student.getScoreBookNum())) {
			students.remove(student.getScoreBookNum());
		}

		students.put(student.getScoreBookNum(), student);

		JSONService.writeStudents(students.values(), context);

		return "redirect:/students";

	}

}
