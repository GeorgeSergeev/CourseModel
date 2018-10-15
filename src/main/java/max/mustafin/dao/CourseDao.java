package max.mustafin.dao;

import max.mustafin.model.Course;

import java.util.List;

public interface CourseDao {
    void create(Course course);
    void update(Course course);
    Course getByNumber(int number);
    List<Course> getAll();
    void delete(Course course);
}
