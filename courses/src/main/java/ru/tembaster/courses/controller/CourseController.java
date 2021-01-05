package ru.tembaster.courses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tembaster.courses.model.Course;
import ru.tembaster.courses.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public String getAll(Model model){
        List<Course> list = courseService.getAll();
        model.addAttribute("courses", list);
        return "courses";
    }
}