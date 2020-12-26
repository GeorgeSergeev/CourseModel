package ru.sorochinsky.service;

import ru.sorochinsky.model.Course;
import ru.sorochinsky.model.Student;

import java.util.List;

/**
 * Service class for {@link ru.sorochinsky.model.Course}
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

public interface CourseService {
    Course addCourse(Course course);
    void delete(Long id);
    Course getByName(String name);
    Course editCourse(Course course);
    List<Course> getAll();

//    Дополнительные методы согласно заданию:
//    - добавить студента (void)
//    - удалить студента (void)
    void addStudent(Course course, Student student);
    void delStudent(Course course, Student student);
}
