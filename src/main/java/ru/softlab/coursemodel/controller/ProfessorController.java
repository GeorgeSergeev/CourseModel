package ru.softlab.coursemodel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.softlab.coursemodel.model.dto.CourseDto;
import ru.softlab.coursemodel.model.dto.EnrollInCourseDto;
import ru.softlab.coursemodel.model.dto.StudentDto;
import ru.softlab.coursemodel.model.dto.validation.scenario.Create;
import ru.softlab.coursemodel.model.dto.validation.scenario.Update;
import ru.softlab.coursemodel.service.CourseService;
import ru.softlab.coursemodel.service.StudentService;

import javax.validation.constraints.NotNull;
import java.util.Collection;

@Validated
@RestController
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    private StudentService service;

    @Autowired
    private CourseService courseService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> findById(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> create(@Validated(Create.class) @RequestBody StudentDto dto) {
        StudentDto response = service.create(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentDto> update(@Validated(Update.class) @RequestBody StudentDto dto) {
        StudentDto response = service.update(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam @NotNull Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/enroll", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> enrollInCourse(@RequestBody EnrollInCourseDto dto) {
        courseService.enrollInCourse(dto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/courses/completed")
    public ResponseEntity<Collection<CourseDto>> findAllCompletedCourses(@PathVariable Integer studentId) {
        Collection<CourseDto> response = service.findAllCompletedCourses(studentId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
