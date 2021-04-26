package ru.khrebtov.university.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khrebtov.university.entity.Professor;
import ru.khrebtov.university.entity.dtoEntity.DtoProfessor;
import ru.khrebtov.university.entity.repository.ProfessorRepository;

import java.util.ArrayList;
import java.util.List;


public class ProfessorServiceImpl implements ProfessorServiceRest {
    Logger logger = LoggerFactory.getLogger(ProfessorServiceImpl.class);

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public List<DtoProfessor> findAll() {
        logger.info("all professor");
        List<DtoProfessor> list = new ArrayList<>();
        for (Professor professor : professorRepository.findAll()) {
            DtoProfessor dtoProfessor = getDtoProfessor(professor);
            list.add(dtoProfessor);
        }
        return list;
    }

    @Override
    public DtoProfessor findById(Long id) {
        logger.info("find professor by id = {}", id);
        Professor professorById = professorRepository.findById(id);
        return getDtoProfessor(professorById);
    }

    private DtoProfessor getDtoProfessor(Professor professor) {
        professor.setCourse(professorRepository.getProfessorCourse(professor.getId()));
        return new DtoProfessor(professor);
    }

    @Override
    public Long countAll() {
        logger.info("count professors");
        return professorRepository.countAll();
    }

    @Override
    public void insert(DtoProfessor professor) {
        logger.info("Try insert professor with id {}", professor.getId());
        if (professor.getId() != null) {
            logger.error("Был передан существующий профессор id!=null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(professor);
    }

    @Override
    public void update(DtoProfessor professor) {
        logger.info("Try update professor with id {}", professor.getId());
        if (professor.getId() == null) {
            logger.error("Был передан не существующий профессор id==null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(professor);
    }

    @Transactional
    public void saveOrUpdate(DtoProfessor professor) {
        logger.info("Saving professor with id {}", professor.getId());
        professorRepository.saveOrUpdate(new Professor(professor));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        logger.info("Deleting professor with id {}", id);
        professorRepository.deleteById(id);
    }
}
