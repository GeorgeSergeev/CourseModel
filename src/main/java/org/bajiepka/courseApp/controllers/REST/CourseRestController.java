package org.bajiepka.courseApp.controllers.REST;

import org.bajiepka.courseApp.domain.Course;
import org.bajiepka.courseApp.repositories.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseRestController {

    private final CourseRepository courseRepository;

    public CourseRestController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/courses")
    public List<Course> all(){
        return (List<Course>) courseRepository.findAll();
    }

    @PostMapping("/courses")
    public Course create(@RequestBody Course course){
        courseRepository.save(course);
        return course;
    }

    @DeleteMapping("/courses/{id}")
    ResponseEntity deleteJob(@PathVariable Long id){

        courseRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }
}
