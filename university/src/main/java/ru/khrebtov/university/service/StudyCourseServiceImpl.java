package ru.khrebtov.university.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khrebtov.university.entity.Student;
import ru.khrebtov.university.entity.StudyCourse;
import ru.khrebtov.university.entity.dtoEntity.DtoStudyCourse;
import ru.khrebtov.university.entity.repository.StudyCourseRepository;


import java.util.ArrayList;
import java.util.List;

@Service
public class StudyCourseServiceImpl implements StudyCourseServiceRest {
    Logger logger = LoggerFactory.getLogger(StudyCourseServiceImpl.class);

    private final StudyCourseRepository studyCourseRepository;

    @Autowired
    public StudyCourseServiceImpl(StudyCourseRepository studyCourseRepository) {
        this.studyCourseRepository = studyCourseRepository;
    }

    @Override
    public List<DtoStudyCourse> findAll() {
        logger.info("all StudyCourse");
        List<DtoStudyCourse> list = new ArrayList<>();
        for (StudyCourse studyCourse : studyCourseRepository.findAll()) {
            DtoStudyCourse dtoStudyCourse = getDtoStudyCourse(studyCourse);
            list.add(dtoStudyCourse);
        }
        return list;
    }

    @Override
    public DtoStudyCourse findById(Long id) {
        logger.info("find studyCourse by id = {}", id);
        StudyCourse studyCourseById = studyCourseRepository.findById(id);
        return getDtoStudyCourse(studyCourseById);
    }

    private DtoStudyCourse getDtoStudyCourse(StudyCourse studyCourse) {
        List<Integer> rating = studyCourseRepository.getRatings(studyCourse.getId());
        studyCourse.setRating(rating);
        Student student = studyCourseRepository.getStudentByStudyCourseId(studyCourse.getId());
        studyCourse.setStudent(student);
        studyCourse.setCourse(studyCourseRepository.getCourseByStudyCourseId(studyCourse.getId()));
        return new DtoStudyCourse(studyCourse);
    }

    @Override
    public Long countAll() {
        logger.info("count studyCourse");
        return studyCourseRepository.countAll();
    }

    @Override
    public void insert(DtoStudyCourse studyCourse) {
        logger.info("Try insert studyCourse with id {}", studyCourse.getId());
        if (studyCourse.getId() != null) {
            logger.error("Был передан существующий studyCourse id!=null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(studyCourse);
    }

    @Override
    public void update(DtoStudyCourse studyCourse) {
        logger.info("Try update professor with id {}", studyCourse.getId());
        if (studyCourse.getId() == null) {
            logger.error("Был передан не существующий профессор id==null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(studyCourse);
    }

    @Transactional
    public void saveOrUpdate(DtoStudyCourse studyCourse) {
        logger.info("Saving studyCourse with id {}", studyCourse.getId());
        studyCourseRepository.saveOrUpdate(new StudyCourse(studyCourse));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        logger.info("Deleting studyCourse with id {}", id);
        studyCourseRepository.deleteById(id);
    }
}
