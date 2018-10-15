package max.mustafin.dao;

import max.mustafin.model.Student;

import java.util.List;

public interface StudentDao {
    void create(Student student);
    void update(Student student);
    Student getByScoreBook(int scoreBookNumber);
    List<Student> getAll();
    void delete(Student student);
    void merge(Student student);
}
