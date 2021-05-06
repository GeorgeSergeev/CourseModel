package ru.khrebtov.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.khrebtov.university.entity.dtoEntity.DtoCourse;
import ru.khrebtov.university.service.CourseService;

import java.util.List;

@RestController
@RequestMapping("/rest/courses")
public class CourseControllerRest {

    private final CourseService courseService;

    @Autowired
    public CourseControllerRest(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DtoCourse> findAll() {
        return courseService.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DtoCourse findById(@PathVariable Long id) {
        return courseService.findById(id);
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long countAll() {
        return courseService.count();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insert(@RequestBody DtoCourse course) {
        courseService.insert(course);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody DtoCourse course) {
        courseService.update(course);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
    }

}
