package dao;

import model.Course;

import java.util.List;

public interface CourseDAO extends AbstractDAO<Course> {

    Course findByName(String name);

    Course findById(int id);

    List<Course> findAll();

}
