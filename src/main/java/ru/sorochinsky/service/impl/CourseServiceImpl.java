package ru.sorochinsky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sorochinsky.model.Course;
import ru.sorochinsky.model.Student;
import ru.sorochinsky.repositiry.CourseRepository;
import ru.sorochinsky.service.CourseService;

import java.util.List;

/**
 * Implementation of {@link ru.sorochinsky.service.CourseService} interface.
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course addCourse(Course course) {
        Course savedCourse = courseRepository.saveAndFlush(course);
        return savedCourse;
    }

    @Override
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    @Override
    public Course getByName(String name) {
        return courseRepository.findByName(name);
    }

    @Override
    public Course editCourse(Course course) {
        return courseRepository.saveAndFlush(course);
    }

    @Override
    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    @Override
    public void addStudent(Course course, Student student) {

    }

    @Override
    public void delStudent(Course course, Student student) {

    }
}
