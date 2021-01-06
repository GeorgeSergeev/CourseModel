package ru.tembaster.courses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tembaster.courses.model.Student;
import ru.tembaster.courses.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {

        return studentRepository.saveAndFlush(student);
    }

    public boolean delete(int id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            studentRepository.delete(student);
            return true;
        }
        return false;
    }

    public Student get(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }
}
