package ru.tembaster.courses.repository;

import org.springframework.stereotype.Repository;
import ru.tembaster.courses.model.Course;
import ru.tembaster.courses.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudentRepository {

    @PersistenceContext
    EntityManager em;

    private StudentJpaRepository studentJpaRepository;

    public StudentRepository(StudentJpaRepository studentJpaRepository) {
        this.studentJpaRepository = studentJpaRepository;
    }

    public Student save(Student student) {
        return studentJpaRepository.save(student);
    }

    public Student findById(Integer id) {
        return studentJpaRepository.findById(id).orElse(null);
    }

    public List<Student> findAll() {
        return studentJpaRepository.findAll();
    }

    public void delete(Student student) {
        studentJpaRepository.delete(student);
    }

    public List<Integer> getAllMarksById(Integer id) {
        return studentJpaRepository.getAllMarksByStudentId(id);
    }

    public List<Course> getWithCourses(Integer id) {
        return studentJpaRepository.getWithCourses(id);
    }
}
