package ru.khrebtov.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khrebtov.entity.Professor;
import ru.khrebtov.repositories.ProfessorRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;

@Stateless
public class ProfessorServiceImpl implements ProfessorServiceRest {
    Logger logger = LoggerFactory.getLogger(ProfessorServiceImpl.class);

    @EJB
    private ProfessorRepository professorRepository;

    @Override
    public List<Professor> findAll() {
        logger.info("all professor");
        return professorRepository.findAll();
    }

    @Override
    public Professor findById(Long id) {
        logger.info("find professor by id = {}",id);
        return professorRepository.findById(id);
    }

    @Override
    public Long countAll() {
        logger.info("count professors");
        return professorRepository.countAll();
    }

    @Override
    public void insert(Professor professor) {
        logger.info("Try insert professor with id {}", professor.getId());
        if (professor.getId() != null) {
            logger.error("Был передан существующий профессор id!=null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(professor);
    }

    @Override
    public void update(Professor professor) {
        logger.info("Try update professor with id {}", professor.getId());
        if (professor.getId() == null) {
            logger.error("Был передан не существующий профессор id==null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(professor);
    }

    @TransactionAttribute
    public void saveOrUpdate(Professor professor) {
        logger.info("Saving professor with id {}", professor.getId());
        professorRepository.saveOrUpdate(professor);
    }

    @Override
    @TransactionAttribute
    public void deleteById(Long id) {
        logger.info("Deleting professor with id {}", id);
        professorRepository.deleteById(id);
    }
}
