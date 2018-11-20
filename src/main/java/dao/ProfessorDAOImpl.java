package dao;

import lombok.NoArgsConstructor;
import model.Professor;
import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;

import java.util.List;

@NoArgsConstructor
public class ProfessorDAOImpl extends AbstractDAOImpl<Professor> implements ProfessorDAO {

    @Override
    public Professor findById(int id) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Professor professor = session
                .get(Professor.class, id);
        session.close();
        return professor;
    }

    @Override
    public List<Professor> findAll() {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        List<Professor> professors = session
                .createQuery("FROM Professor")
                .list();
        session.close();
        return professors;
    }
}
