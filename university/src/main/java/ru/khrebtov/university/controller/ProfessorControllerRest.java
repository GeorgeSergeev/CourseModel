package ru.khrebtov.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.khrebtov.university.entity.dtoEntity.DtoProfessor;
import ru.khrebtov.university.service.ProfessorServiceImpl;

import java.util.List;

@RestController
@RequestMapping(path = "/professorRest")
public class ProfessorControllerRest {
    private final ProfessorServiceImpl professorService;

    @Autowired
    public ProfessorControllerRest(ProfessorServiceImpl professorService) {
        this.professorService = professorService;
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DtoProfessor> findAll() {
        return professorService.findAll();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public DtoProfessor findById(@PathVariable(value = "id") Long id) {
        return professorService.findById(id);
    }

    @RequestMapping(path = "/count", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Long countAll() {
        return professorService.countAll();
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void insert(DtoProfessor professor) {
        professorService.insert(professor);
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(DtoProfessor professor) {
        professorService.update(professor);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable(value = "id") Long id) {
        professorService.deleteById(id);
    }

    public void saveOrUpdate(DtoProfessor professor) {
        professorService.saveOrUpdate(professor);
    }
}
