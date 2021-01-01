package dao;

import lombok.NoArgsConstructor;
import org.hibernate.Transaction;
import util.SessionInstance;

@NoArgsConstructor
public class AbstractDAOImpl<T> implements AbstractDAO<T> {

    @Override
    public void save(T o) {
        Transaction tx1 = SessionInstance.getInstance().getSession().beginTransaction();
        SessionInstance.getInstance().getSession().save(o);
        tx1.commit();
    }

    @Override
    public void update(T o) {
        Transaction tx1 = SessionInstance.getInstance().getSession().beginTransaction();
        System.out.println(".1");
        SessionInstance.getInstance().getSession().update(o);
        System.out.println(".2");
        tx1.commit();
        System.out.println(".3");
    }

    @Override
    public void delete(T o) {
        Transaction tx1 = SessionInstance.getInstance().getSession().beginTransaction();
        SessionInstance.getInstance().getSession().delete(o);
        tx1.commit();
    }

}
