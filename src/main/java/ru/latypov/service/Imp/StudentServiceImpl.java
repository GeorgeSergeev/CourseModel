package ru.latypov.service.Imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.latypov.exception.StudentNotFound;
import ru.latypov.model.Student;
import ru.latypov.repository.StudentRepository;
import ru.latypov.service.StudentService;

import java.util.List;
import java.util.Optional;


@Service("EmployeeService")
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> retrieveStudent() {
        List<Student> student = studentRepository.findAll();
        return student;
    }

    public Student getStudent(Integer id) throws StudentNotFound {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFound((id)));

    }

    public Student getStudent(Student student) {
        Optional<Student> optEmp1 = studentRepository.findById(student.getId());
        return optEmp1.get();
    }

    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }
}