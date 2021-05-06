package ru.khrebtov.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.khrebtov.university.entity.dtoEntity.DtoStudent;
import ru.khrebtov.university.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/rest/students")
public class StudentControllerRest {
    private final StudentService studentService;

    @Autowired
    public StudentControllerRest(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DtoStudent> findAll() {
        return studentService.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DtoStudent findById(@PathVariable Long id) {
        return studentService.findById(id);
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long countAll() {
        return studentService.count();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insert(@RequestBody DtoStudent student) {
        studentService.insert(student);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody DtoStudent student) {
        studentService.update(student);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studentService.deleteById(id);
    }
}
