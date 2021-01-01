package dao;

import model.Course;
import model.Score;
import model.Student;

import java.util.List;

public interface ScoreDAO extends AbstractDAO<Score> {

    List<Score> getScoresByStudentAndCourse(Student student, Course course);

}
