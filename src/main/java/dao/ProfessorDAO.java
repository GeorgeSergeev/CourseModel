package dao;

import model.Professor;

import java.util.List;

public interface ProfessorDAO extends AbstractDAO<Professor> {

    Professor findById(int id);

    List<Professor> findAll();

}
