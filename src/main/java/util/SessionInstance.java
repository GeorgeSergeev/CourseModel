package util;

import lombok.Getter;
import org.hibernate.Session;

public class SessionInstance {

    @Getter
    private Session session = HibernateSessionFactoryUtil
            .getSessionFactory()
            .openSession();

    private static SessionInstance ourInstance = new SessionInstance();

    public static SessionInstance getInstance() {
        return ourInstance;
    }

    private SessionInstance() {
    }
}
