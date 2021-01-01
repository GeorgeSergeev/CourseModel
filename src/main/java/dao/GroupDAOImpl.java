package dao;

import lombok.NoArgsConstructor;
import model.Course;
import model.StudentsGroup;
import model.Student;
import util.SessionInstance;

import java.util.List;

@NoArgsConstructor
public class GroupDAOImpl extends AbstractDAOImpl<StudentsGroup> implements GroupDAO {

    @Override
    public StudentsGroup getGroupByStudentAndCourse(Student student, Course course) {
        List<StudentsGroup> list = ((List<StudentsGroup>) SessionInstance.getInstance().getSession()
                .createQuery("FROM StudentsGroup WHERE student = '" + student.getId() + "' AND course = '" + course.getId() + "'")
                .list());
        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

}
