package ru.softlab.coursemodel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.softlab.coursemodel.model.dto.ProfessorDto;
import ru.softlab.coursemodel.model.dto.validation.scenario.Create;
import ru.softlab.coursemodel.model.dto.validation.scenario.Update;
import ru.softlab.coursemodel.service.ProfessorService;
import ru.softlab.coursemodel.service.ReportCreator;

import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequestMapping("/professors")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @Autowired
    private ReportCreator reportCreator;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfessorDto> findById(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfessorDto> create(@Validated(Create.class) @RequestBody ProfessorDto dto) {
        ProfessorDto response = service.create(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProfessorDto> update(@Validated(Update.class) @RequestBody ProfessorDto dto) {
        ProfessorDto response = service.update(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestParam @NotNull Integer id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/create-report")
    public ResponseEntity<Void> createReport() {
        reportCreator.createXlsxReport();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
