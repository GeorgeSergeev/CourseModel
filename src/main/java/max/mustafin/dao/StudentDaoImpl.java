package max.mustafin.dao;

import max.mustafin.model.Student;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void create(Student student) {
        sessionFactory.getCurrentSession().save(student);
    }
    @Override
    public void update(Student student) {
        sessionFactory.getCurrentSession().saveOrUpdate(student);
    }
    @Override
    public Student getByScoreBook(int scoreBookNumber) {
        return (Student) sessionFactory.getCurrentSession().get(Student.class,scoreBookNumber);
    }
    @Override
    public List<Student> getAll() {
        return sessionFactory.getCurrentSession().createQuery("from Student order by name").list();
    }
    @Override
    public void delete(Student student) {
        sessionFactory.getCurrentSession().delete(student);
    }

    @Override
    public void merge(Student student) {
        sessionFactory.getCurrentSession().merge(student);
    }
}
