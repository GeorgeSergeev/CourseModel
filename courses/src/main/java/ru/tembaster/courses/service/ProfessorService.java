package ru.tembaster.courses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tembaster.courses.model.Professor;
import ru.tembaster.courses.repository.ProfessorRepository;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor save(Professor professor) {
        return professorRepository.saveAndFlush(professor);
    }

    public boolean delete(int id) {
        return professorRepository.delete(id) != 0;
    }

    public Professor get(int id) {
        return professorRepository.findById(id).orElse(null);
    }

    public List<Professor> getAll() {
        return professorRepository.findAll();
    }

}
