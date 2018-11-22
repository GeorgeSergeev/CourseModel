package dao;

import lombok.NoArgsConstructor;
import model.Student;
import util.SessionInstance;

import java.util.List;

@NoArgsConstructor
public class StudentDAOImpl extends AbstractDAOImpl<Student> implements StudentDAO {

    @Override
    public Student findByName(String name) {
        List<Student> list = ((List<Student>) SessionInstance.getInstance().getSession()
                .createQuery("FROM Student WHERE name = '" + name + "'").list());
        if (list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Student findById(int id) {
        Student student = SessionInstance.getInstance().getSession()
                .get(Student.class, id);
        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = SessionInstance.getInstance().getSession()
                .createQuery("FROM Student")
                .list();
        return students;
    }
}
