package dao;

import lombok.NoArgsConstructor;
import model.Course;
import util.SessionInstance;

import java.util.List;

@NoArgsConstructor
public class CourseDAOImpl extends AbstractDAOImpl<Course> implements CourseDAO {

    @Override
    public Course findById(int id) {
        Course course = SessionInstance.getInstance().getSession()
                .get(Course.class, id);
        return course;
    }

    @Override
    public List<Course> findAll() {
        List<Course> courses = SessionInstance.getInstance().getSession()
                .createQuery("FROM Course")
                .list();
        return courses;
    }

}
