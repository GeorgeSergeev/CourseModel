package dao;

import lombok.NoArgsConstructor;
import model.Course;
import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;

import java.util.List;

@NoArgsConstructor
public class CourseDAOImpl extends AbstractDAOImpl<Course> implements CourseDAO {

    @Override
    public Course findById(int id) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Course course = session
                .get(Course.class, id);
        session.close();
        return course;
    }

    @Override
    public List<Course> findAll() {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        List<Course> courses = session
                .createQuery("FROM Course")
                .list();
        session.close();
        return courses;
    }

}
