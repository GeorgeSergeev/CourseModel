package ru.softlab.coursemodel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.softlab.coursemodel.model.dto.CompletingCourseDto;
import ru.softlab.coursemodel.model.dto.validation.scenario.Create;
import ru.softlab.coursemodel.model.dto.validation.scenario.Update;
import ru.softlab.coursemodel.service.CompletingCourseService;

import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/completing-courses")
public class CompletingCourseController {

    @Autowired
    private CompletingCourseService service;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompletingCourseDto> findById(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompletingCourseDto> create(@Validated(Create.class) @RequestBody CompletingCourseDto dto) {
        CompletingCourseDto response = service.create(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompletingCourseDto> update(@Validated(Update.class) @RequestBody CompletingCourseDto dto) {
        CompletingCourseDto response = service.update(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam @NotNull Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/average-mark")
    public ResponseEntity<Float> findAverageMark(@RequestParam @NotNull Integer studentId,
                                                 @RequestParam @NotNull Integer courseId) {
        float mark = service.findAverageMark(studentId, courseId);
        return new ResponseEntity<>(mark, HttpStatus.OK);
    }

    @PostMapping("summarise-mark")
    public ResponseEntity<Float> summariseMark(@RequestParam @NotNull Integer studentId,
                                               @RequestParam @NotNull Integer courseId) {
        float mark = service.summariseMark(studentId, courseId);
        return new ResponseEntity<>(mark, HttpStatus.OK);
    }
}
