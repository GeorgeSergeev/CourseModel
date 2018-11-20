package service;

import dao.*;
import exception.NoSuchStudentOnCourse;
import lombok.NoArgsConstructor;
import model.Course;
import model.Score;
import model.Student;

@NoArgsConstructor
public class StudentService {

    private StudentDAO dao = new StudentDAOImpl();
    private ScoreDAO scoreDAO = new ScoreDAOImpl();
    private GroupDAO groupDAO = new GroupDAOImpl();

    public void addScoreForStudent(Student student, Course course, int score) {
        if (groupDAO.getGroupByStudentAndCourse(student, course) != null) {
            scoreDAO.save(new Score(student, course, score));
        } else {
            throw new NoSuchStudentOnCourse("Student id '" + student.getId() + "' is not attending to course id '" + course.getId() + "'.");
        }
    }

    public void addStudent(Student student) {
        dao.save(student);
    }

}
