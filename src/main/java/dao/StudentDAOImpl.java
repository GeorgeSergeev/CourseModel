package dao;

import lombok.NoArgsConstructor;
import model.Student;
import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;

import java.util.List;

@NoArgsConstructor
public class StudentDAOImpl extends AbstractDAOImpl<Student> implements StudentDAO {

    @Override
    public Student findById(int id) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Student student = session
                .get(Student.class, id);
        session.close();
        return student;
    }

    @Override
    public List<Student> findAll() {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        List<Student> students = session
                .createQuery("FROM Student")
                .list();
        session.close();
        return students;
    }
}
