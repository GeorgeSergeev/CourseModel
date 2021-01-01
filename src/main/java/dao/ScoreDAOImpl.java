package dao;

import lombok.NoArgsConstructor;
import model.Course;
import model.Score;
import model.Student;
import util.SessionInstance;

import java.util.List;

@NoArgsConstructor
public class ScoreDAOImpl extends AbstractDAOImpl<Score> implements ScoreDAO {

    @Override
    public List<Score> getScoresByStudentAndCourse(Student student, Course course) {
        List<Score> list = ((List<Score>) SessionInstance.getInstance().getSession()
                .createQuery("FROM Score WHERE student = '" + student.getId() + "' AND course = '" + course.getId() + "'")
                .list());
        if (list.size() > 0) {
            return list;
        } else {
            return null;
        }
    }

}
