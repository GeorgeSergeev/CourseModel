package service;

import dao.*;
import exception.NoSuchStudentOnCourse;
import lombok.NoArgsConstructor;
import model.Course;
import model.Score;
import model.Student;

import java.util.List;

@NoArgsConstructor
public class StudentService {

    private StudentDAO dao = new StudentDAOImpl();
    private ScoreDAO scoreDAO = new ScoreDAOImpl();
    private GroupDAO groupDAO = new GroupDAOImpl();
    private CourseDAO courseDAO = new CourseDAOImpl();

    public void addScoreForStudent(Student student, Course course, int score) {
        if (groupDAO.getGroupByStudentAndCourse(student, course) != null) {
            Score newScore = new Score(student, course, score);
            scoreDAO.save(newScore);
            student.addSCore(newScore);
            dao.update(student);
            course.addSCore(newScore);
            courseDAO.update(course);
        } else {
            throw new NoSuchStudentOnCourse("Student id '" + student.getId() + "' is not attending to course id '" + course.getId() + "'.");
        }
    }

    public void removeAllScoresForStudentOnCourse(Student student, Course course) {
        List<Score> scores = scoreDAO.getScoresByStudentAndCourse(student, course);
        if (scores != null && scores.size() > 0) {
            for (Score score :scores) {
                student.removeScore(score);
                course.removeScore(score);
                scoreDAO.delete(score);
            }

        }
    }

    public void addStudent(Student student) {
        if (dao.findById(student.getId()) == null) {
            dao.save(student);
        } else {
            System.out.println("Student already exist");
        }
    }

}
