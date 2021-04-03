package ru.khrebtov.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khrebtov.entity.Professor;
import ru.khrebtov.entity.dtoEntity.DtoProfessor;
import ru.khrebtov.repositories.ProfessorRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ProfessorServiceImpl implements ProfessorServiceRest {
    Logger logger = LoggerFactory.getLogger(ProfessorServiceImpl.class);

    @EJB
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

    @TransactionAttribute
    public void saveOrUpdate(DtoProfessor professor) {
        logger.info("Saving professor with id {}", professor.getId());
        professorRepository.saveOrUpdate(new Professor(professor));
    }

    @Override
    @TransactionAttribute
    public void deleteById(Long id) {
        logger.info("Deleting professor with id {}", id);
        professorRepository.deleteById(id);
    }
}
