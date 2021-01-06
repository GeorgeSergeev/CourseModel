package ru.tembaster.courses.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tembaster.courses.model.Student;
import ru.tembaster.courses.service.CourseService;
import ru.tembaster.courses.service.StudentService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/students")
public class StudentRestController {

    StudentService studentService;
    CourseService courseService;

    @Autowired
    public StudentRestController(StudentService studentService, CourseService courseService) {
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Student>> getAll() {
        List<Student> result = studentService.getAll();
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> get(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Student student = studentService.get(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
    
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> create(@RequestBody @Valid Student student) {
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> update(@RequestBody @Valid Student student, @PathVariable("id") Integer id) {
        if (student == null || id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        student.setId(id);
        studentService.save(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> delete(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Student student = studentService.get(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
