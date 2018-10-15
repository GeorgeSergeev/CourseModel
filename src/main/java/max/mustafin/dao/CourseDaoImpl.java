package max.mustafin.dao;

import max.mustafin.model.Course;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void create(Course course) {
        sessionFactory.getCurrentSession().save(course);
    }
    @Override
    public void update(Course course) {
        sessionFactory.getCurrentSession().saveOrUpdate(course);
    }
    @Override
    public Course getByNumber(int number) {
        return (Course) sessionFactory.getCurrentSession().get(Course.class,number);
    }
    @Override
    public List<Course> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Course order by name").list();
    }
    @Override
    public void delete(Course course) {
        sessionFactory.getCurrentSession().delete(course);
    }
}
