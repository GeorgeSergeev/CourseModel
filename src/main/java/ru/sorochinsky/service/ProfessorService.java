package ru.sorochinsky.service;

import ru.sorochinsky.model.Professor;

import java.util.List;

/**
 * Service class for {@link ru.sorochinsky.model.Professor}
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

public interface ProfessorService {
    Professor addProfessor(Professor professor);
    void delete(Long id);
    Professor getByName(String name);
    Professor editProfessor(Professor professor);
    List<Professor> getAll();
}
