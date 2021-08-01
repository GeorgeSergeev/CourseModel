package ru.softlab.coursemodel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.softlab.coursemodel.model.dto.CourseDto;
import ru.softlab.coursemodel.model.dto.EnrollInCourseDto;
import ru.softlab.coursemodel.model.dto.validation.scenario.Create;
import ru.softlab.coursemodel.model.dto.validation.scenario.Update;
import ru.softlab.coursemodel.service.CourseService;

@Validated
@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDto> getById(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDto> create(@Validated(Create.class) @RequestBody CourseDto dto) {
        CourseDto response = service.create(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CourseDto> update(@Validated(Update.class) @RequestBody CourseDto dto) {
        CourseDto response = service.update(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/enroll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> enrollStudent(@RequestBody EnrollInCourseDto dto) {
        service.enrollInCourse(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/enroll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> removeStudent(@RequestBody EnrollInCourseDto dto) {
        service.removeFromCourse(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
