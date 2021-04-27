package ru.khrebtov.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.khrebtov.university.entity.dtoEntity.DtoProfessor;
import ru.khrebtov.university.service.ProfessorServiceImpl;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/professorRest")
public class ProfessorControllerRest {
    private final ProfessorServiceImpl professorService;

    @Autowired
    public ProfessorControllerRest(ProfessorServiceImpl professorService) {
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
        return professorService.countAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insert(DtoProfessor professor) {
        professorService.insert(professor);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(DtoProfessor professor) {
        professorService.update(professor);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable(value = "id") Long id) {
        professorService.deleteById(id);
    }
}
