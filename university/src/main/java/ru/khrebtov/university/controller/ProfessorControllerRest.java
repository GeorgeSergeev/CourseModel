package ru.khrebtov.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.khrebtov.university.entity.dtoEntity.DtoProfessor;
import ru.khrebtov.university.service.ProfessorService;

import java.util.List;

@RestController
@RequestMapping("/rest/professors")
public class ProfessorControllerRest {
    private final ProfessorService professorService;

    @Autowired
    public ProfessorControllerRest(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DtoProfessor> findAll() {
        return professorService.findAll();
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DtoProfessor findById(@PathVariable Long id) {
        return professorService.findById(id);
    }

    @GetMapping(path = "/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public Long countAll() {
        return professorService.count();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insert(@RequestBody DtoProfessor professor) {
        professorService.insert(professor);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody DtoProfessor professor) {
        professorService.update(professor);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        professorService.deleteById(id);
    }
}
