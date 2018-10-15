package max.mustafin.controller;

import max.mustafin.model.Course;
import max.mustafin.model.Professor;
import max.mustafin.model.Student;
import max.mustafin.service.CourseService;
import max.mustafin.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/courseController")
public class CourseController {
    @Autowired
    private CourseService courseService;
    @RequestMapping(value = "/preCreateCourse", method = RequestMethod.GET)
    public String preCreateCourse(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        return "create-course";
    }
    @RequestMapping(value = "/createCourse", method = RequestMethod.POST)
    public String createCourse(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        float price = Float.valueOf(request.getParameter("price"));
        String professorName = request.getParameter("professor");
        Professor professor = new Professor(professorName);
        Course course = new Course(name,price);
        course.setProfessor(professor);
        courseService.update(course);
        return "create-course";
    }
    @RequestMapping(value = "/getAllCourses", method = RequestMethod.GET)
    public String getAllCourses(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        List<Course> allCourses = courseService.getAll();
        request.setAttribute("allCourses",allCourses);
        return "show-all-courses";
    }
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteCourse(@PathVariable("id") String id, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        Course course = courseService.getByNumber(Integer.parseInt(id));
        courseService.delete(course);
        List<Course> allCourses = courseService.getAll();
        request.setAttribute("allCourses",allCourses);
        return "show-all-courses";
    }
    @RequestMapping(value = "preUpdate/{id}", method = RequestMethod.GET)
    public String preUpdateCourse(@PathVariable("id") String id, ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        Course course = courseService.getByNumber(Integer.parseInt(id));
        request.setAttribute("course",course);
        return "update-course";
    }
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateCourse(ModelMap model, HttpServletRequest request, HttpServletResponse response) {
        int number = Integer.parseInt(request.getParameter("number"));
        Course course = courseService.getByNumber(number);
        String name = request.getParameter("name");
        float price = Float.valueOf(request.getParameter("price"));
        Professor professor = course.getProfessor();
        professor.setName(request.getParameter("professor"));
        course.setName(name);
        course.setPrice(price);
        courseService.update(course);
        return "create-course";
    }
}
