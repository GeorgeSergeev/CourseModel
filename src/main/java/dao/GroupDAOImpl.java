package dao;

import lombok.NoArgsConstructor;
import model.Course;
import model.StudentsGroup;
import model.Student;
import org.hibernate.Session;
import util.HibernateSessionFactoryUtil;

import java.util.List;

@NoArgsConstructor
public class GroupDAOImpl extends AbstractDAOImpl<StudentsGroup> implements GroupDAO {

    @Override
    public StudentsGroup getGroupByStudentAndCourse(Student student, Course course) {
        Session session = HibernateSessionFactoryUtil
                .getSessionFactory()
                .openSession();
        List<StudentsGroup> list = ((List<StudentsGroup>) session
                .createQuery("FROM StudentsGroup WHERE student = '" + student.getId() + "' AND course = '" + course.getId() + "'")
                .list());
        session.close();
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

}
