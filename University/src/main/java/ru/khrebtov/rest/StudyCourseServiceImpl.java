package ru.khrebtov.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khrebtov.entity.StudyCourse;
import ru.khrebtov.repositories.StudyCourseRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;

@Stateless
public class StudyCourseServiceImpl implements StudyCourseServiceRest {
    Logger logger = LoggerFactory.getLogger(StudyCourseServiceImpl.class);

    @EJB
    private StudyCourseRepository studyCourseRepository;

    @Override
    public List<StudyCourse> findAll() {
        logger.info("all StudyCourse");
        return studyCourseRepository.findAll();
    }

    @Override
    public StudyCourse findById(Long id) {
        logger.info("find studyCourse by id = {}",id);
        return studyCourseRepository.findById(id);
    }

    @Override
    public Long countAll() {
        logger.info("count studyCourse");
        return studyCourseRepository.countAll();
    }

    @Override
    public void insert(StudyCourse studyCourse) {
        logger.info("Try insert studyCourse with id {}", studyCourse.getId());
        if (studyCourse.getId() == null) {
            logger.error("Был передан не существующий studyCourse id==null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(studyCourse);
    }

    @Override
    public void update(StudyCourse studyCourse) {
        logger.info("Try update professor with id {}", studyCourse.getId());
        if (studyCourse.getId() == null) {
            logger.error("Был передан не существующий профессор id==null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(studyCourse);
    }

    @TransactionAttribute
    public void saveOrUpdate(StudyCourse studyCourse) {
        logger.info("Saving studyCourse with id {}", studyCourse.getId());
        studyCourseRepository.saveOrUpdate(studyCourse);
    }

    @Override
    @TransactionAttribute
    public void deleteById(Long id) {
        logger.info("Deleting studyCourse with id {}", id);
        studyCourseRepository.deleteById(id);
    }
}
