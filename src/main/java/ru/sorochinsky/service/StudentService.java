package ru.sorochinsky.service;

import ru.sorochinsky.model.Course;
import ru.sorochinsky.model.CourseProgress;
import ru.sorochinsky.model.Student;

import java.util.List;

/**
 * Service class for {@link ru.sorochinsky.model.Student}
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

public interface StudentService {
    Student addStudent(Student student);
    void delete(Long id);
    Student getByName(String name);
    Student editStudent(Student student);
    List<Student> getAll();

//    Дополнительные методы согласно заданию:
//    - записаться на курс (void)
//    - получить список прослушанных курсов (void / проверка по полю  "boolean completed")
    void addStudentInCourse(Course course, Student student);
    void listEndCourse(List<CourseProgress> completed);
}