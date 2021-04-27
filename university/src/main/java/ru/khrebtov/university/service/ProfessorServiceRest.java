package ru.khrebtov.university.service;


import org.springframework.stereotype.Service;
import ru.khrebtov.university.entity.dtoEntity.DtoProfessor;

import java.util.List;


public interface ProfessorServiceRest {

    List<DtoProfessor> findAll();

    DtoProfessor findById(Long id);

    Long countAll();

    void insert(DtoProfessor professor);

    void update(DtoProfessor professor);

    void deleteById(Long id);

    void saveOrUpdate(DtoProfessor professor);

}
