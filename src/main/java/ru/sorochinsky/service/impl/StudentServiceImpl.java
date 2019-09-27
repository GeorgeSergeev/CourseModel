package ru.sorochinsky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sorochinsky.model.Course;
import ru.sorochinsky.model.CourseProgress;
import ru.sorochinsky.model.Student;
import ru.sorochinsky.repositiry.StudentRepository;
import ru.sorochinsky.service.StudentService;

import java.util.List;

/**
 * Implementation of {@link ru.sorochinsky.service.StudentService} interface.
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student addStudent(Student student) {
        Student savedStudent = studentRepository.saveAndFlush(student);
        return savedStudent;
    }

    @Override
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student getByName(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    public Student editStudent(Student student) {
        return studentRepository.saveAndFlush(student);
    }

    @Override
    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    @Override
    public void addStudentInCourse(Course course, Student student) {

    }

    @Override
    public void listEndCourse(List<CourseProgress> completed) {

    }
}
