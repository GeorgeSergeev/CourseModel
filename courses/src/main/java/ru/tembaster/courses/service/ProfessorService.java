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
        return professorRepository.save(professor);
    }

    public boolean delete(int id) {
        Professor professor = professorRepository.findById(id).orElse(null);
        if (professor != null) {
            professorRepository.delete(professor);
            return true;
        }
        return false;
    }

    public Professor get(int id) {
        return professorRepository.findById(id).orElse(null);
    }

    public List<Professor> getAll() {
        return (List<Professor>) professorRepository.findAll();
    }


}
