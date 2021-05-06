package ru.khrebtov.university.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khrebtov.university.entity.Professor;
import ru.khrebtov.university.entity.dtoEntity.DtoProfessor;
import ru.khrebtov.university.repo.ProfessorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProfessorService implements AbstractService<DtoProfessor> {
    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<DtoProfessor> findAll() {
        log.info("all professor");
        List<DtoProfessor> professors = new ArrayList<>();
        professorRepository.findAll().forEach(p-> professors.add(getDtoProfessor(p)));
        return professors;
    }

    private DtoProfessor getDtoProfessor(Professor professor) {
        professor.setCourse(professorRepository.getProfessorsCourses(professor.getId()));
        return new DtoProfessor(professor);
    }

    @Override
    public DtoProfessor findById(Long id) {
        log.info("find professor by id = {}", id);
        return getDtoProfessor(professorRepository.findById(id));
    }

    @Override
    public Long count() {
        log.info("count professors");
        return professorRepository.count();
    }

    @Override
    public void insert(DtoProfessor professor) {
        log.info("Try insert professor with id {}", professor.getId());
        if (professor.getId() != null) {
            log.error("Был передан существующий профессор id!=null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(professor);
    }

    @Override
    public void update(DtoProfessor professor) {
        log.info("Try update professor with id {}", professor.getId());
        if (professor.getId() == null) {
            log.error("Был передан не существующий профессор id==null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(professor);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting professor with id {}", id);
        professorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void saveOrUpdate(DtoProfessor dtoProfessor) {
        log.info("Saving/updating professor with id {}", dtoProfessor.getId());
        Professor professor = new Professor(dtoProfessor);
        professorRepository.saveOrUpdate(professor);
    }

}
