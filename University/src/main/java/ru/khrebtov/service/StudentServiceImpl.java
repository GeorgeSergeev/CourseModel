package ru.khrebtov.service;

import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.DTOentity.CourseRepr;
import ru.khrebtov.entity.DTOentity.StudentRepr;
import ru.khrebtov.entity.Student;
import ru.khrebtov.repositories.StudentRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class StudentServiceImpl implements StudentService {

    @EJB
    private StudentRepository studentRepository;

    @Override
    public List<StudentRepr> findAll() {
        return studentRepository.findAll().stream().map(StudentRepr::new).collect(Collectors.toList());
    }

    @Override
    public StudentRepr findById(Long id) {
        Student student = studentRepository.findById(id);
        if (student != null) {
            return new StudentRepr(student);
        }
        return null;
    }

    @Override
    @TransactionAttribute
    public void merge(StudentRepr student) {
//        Student merged = studentRepository.merge(new Student(student));
//        student.setId(merged.getId());
    }

    @Override
    @TransactionAttribute
    public void deleteById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Long countAll() {
        return studentRepository.countAll();
    }

    @Override
    @TransactionAttribute
    public List<Course> getStudentCourses(StudentRepr student) {
        return studentRepository.getStudentCourses(student);
    }

}
