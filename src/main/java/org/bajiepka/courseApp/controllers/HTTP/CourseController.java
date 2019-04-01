package org.bajiepka.courseApp.controllers.HTTP;

import org.bajiepka.courseApp.domain.Course;
import org.bajiepka.courseApp.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/activity")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping(value = "/courses")
    public String getAllCourses(Model model) {
        model.addAttribute("courses", service.getCourses());
        return "courses";
    }

    @GetMapping(value = "/course")
    public String findCourseById(@RequestParam Long id, Model model) {
        model.addAttribute("course", service.getCourseById(id));
        return "course";
    }

    @GetMapping(value = "/dismiss")
    public String dismissCourse(@RequestParam Long id){
        service.dismissCourse(id);
        return "redirect:/activity/courses";
    }

    @GetMapping(value = "/preview-course")
    public String previewAsJSON(@RequestParam Long id, Model model){
        model.addAttribute("course", service.getCourseById(id));
        model.addAttribute("json", service.viewCourseAsJSON(id));
        return "preview-course";
    }

    @GetMapping(value = "/preview-all-courses")
    public String previewAllAsJSON(@RequestParam boolean toFile, Model model){
        model.addAttribute("toFile",    toFile);
        model.addAttribute("json",      service.viewAllCoursesAsJSON(toFile));
        return "preview-course";
    }

    @GetMapping(value = "/register-course")
    public String signUpForUniversity(Model model){
        model.addAttribute("course", new Course());
        return "register-course";
    }

    @GetMapping(value = "/edit-course")
    public String editStudent(@RequestParam Long id, Model model){
        model.addAttribute("course", service.getCourseById(id));
        return "edit-course";
    }

    @PostMapping(value = "/edit-course")
    public String saveEditedStudent(@Valid Course course, BindingResult result, Model model){
        service.updateCourse(course);
        return "redirect:/activity/courses";
    }

    @PostMapping(value = "/register-course")
    public String goToUniversity(@Valid Course course, BindingResult result, Model model){
        if (result.hasErrors()){
            return "edit-student";
        }
        boolean created = service.saveCourse(course);
        return "redirect:/activity/courses";
    }

}
