package ru.khrebtov.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.khrebtov.university.entity.dtoEntity.DtoStudyCourse;
import ru.khrebtov.university.service.StudyCourseService;

import java.util.List;

@RestController
@RequestMapping("/rest/studies")
public class StudyCourseControllerRest {
    private final StudyCourseService studyCourseService;

    @Autowired
    public StudyCourseControllerRest(StudyCourseService studyCourseService) {
        this.studyCourseService = studyCourseService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DtoStudyCourse> findAll() {
        return studyCourseService.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DtoStudyCourse findById(@PathVariable Long id) {
        return studyCourseService.findById(id);
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long countAll() {
        return studyCourseService.count();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insert(@RequestBody DtoStudyCourse studyCourse) {
        studyCourseService.insert(studyCourse);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody DtoStudyCourse studyCourse) {
        studyCourseService.update(studyCourse);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        studyCourseService.deleteById(id);
    }
}
