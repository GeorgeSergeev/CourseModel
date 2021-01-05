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

        public Student save(Student professor) {
        return studentRepository.saveAndFlush(professor);
    }

    public boolean delete(int id) {
        return studentRepository.delete(id) != 0;
    }

    public Student get(int id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

}
