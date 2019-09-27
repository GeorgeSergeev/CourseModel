package ru.sorochinsky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sorochinsky.model.Professor;
import ru.sorochinsky.repositiry.ProfessorRepository;
import ru.sorochinsky.service.ProfessorService;

import java.util.List;

/**
 * Implementation of {@link ru.sorochinsky.service.ProfessorService} interface.
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Override
    public Professor addProfessor(Professor professor) {
        Professor savedProfessor = professorRepository.saveAndFlush(professor);
        return savedProfessor;
    }

    @Override
    public void delete(Long id) {
        professorRepository.deleteById(id);
    }

    @Override
    public Professor getByName(String name) {
        return professorRepository.findByName(name);
    }

    @Override
    public Professor editProfessor(Professor professor) {
        return professorRepository.saveAndFlush(professor);
    }

    @Override
    public List<Professor> getAll() {
        return professorRepository.findAll();
    }
}
