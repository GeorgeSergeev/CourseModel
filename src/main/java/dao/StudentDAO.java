package dao;

import model.Student;

import java.util.List;

public interface StudentDAO extends AbstractDAO<Student> {

    Student findById(int id);

    List<Student> findAll();

}
