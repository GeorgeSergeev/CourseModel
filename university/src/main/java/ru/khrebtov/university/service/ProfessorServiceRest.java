package ru.khrebtov.university.service;



import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import ru.khrebtov.university.entity.dtoEntity.DtoProfessor;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping(value = "/professorRest")
public interface ProfessorServiceRest {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<DtoProfessor> findAll();

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    DtoProfessor findById(@PathParam("id") Long id);

    @GetMapping(path = "/count",produces = MediaType.APPLICATION_JSON_VALUE)
    Long countAll();

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void insert(DtoProfessor professor);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    void update(DtoProfessor professor);

    @DeleteMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    void deleteById(@PathParam("id") Long id);

    void saveOrUpdate(DtoProfessor professor);

}
