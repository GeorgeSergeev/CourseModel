package ru.khrebtov.service;

import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.DTOentity.CourseRepr;
import ru.khrebtov.entity.DTOentity.StudentRepr;

import javax.ejb.Local;
import java.util.List;

@Local
public interface StudentService {
    List<StudentRepr> findAll();

    StudentRepr findById(Long id);

    void merge(StudentRepr user);

    void deleteById(Long id);

    Long countAll();

    List<Course> getStudentCourses(StudentRepr student);
}
