package ru.latypov.service;


import ru.latypov.exception.StudentNotFound;
import ru.latypov.model.Student;

import java.util.List;
/*Набор методов для контролера Студент */
public interface StudentService {
    public List<Student> retrieveStudent();

    public Student getStudent(Integer id) throws StudentNotFound;

    public void saveStudent(Student student);

    public Student updateStudent(Student student);

    public Student getStudent(Student student);


}
