package ru.tembaster.courses.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.tembaster.courses.model.Professor;
import ru.tembaster.courses.service.ProfessorService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/rest/professors")
public class ProfessorRestController {

    ProfessorService professorService;

    @Autowired
    public ProfessorRestController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Professor>> getAll() {
        List<Professor> result = professorService.getAll();
        if (result == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> get(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Professor professor = professorService.get(id);
        if (professor == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> create(@RequestBody @Valid Professor professor) {
        if (professor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        professorService.save(professor);
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> update(@RequestBody @Valid Professor professor, @PathVariable("id") Integer id) {
        if (professor == null || id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Professor fromDB = professorService.get(id);
        if (fromDB == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        professor.setId(id);
        professorService.save(professor);
        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Professor> delete(@PathVariable("id") Integer id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Professor fromDB = professorService.get(id);
        if (fromDB == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        professorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
