package dao;

import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateSessionFactoryUtil;

@NoArgsConstructor
public class AbstractDAOImpl<T> implements AbstractDAO<T> {

    @Override
    public void save(T o) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(o);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(T o) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(o);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(T o) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(o);
        tx1.commit();
        session.close();
    }

}
