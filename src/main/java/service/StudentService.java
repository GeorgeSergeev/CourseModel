package service;

import dao.*;
import exception.NoSuchStudentOnCourse;
import lombok.NoArgsConstructor;
import model.Course;
import model.Score;
import model.Student;
import model.StudentsGroup;
import util.Services;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class StudentService {

    private StudentDAO dao = new StudentDAOImpl();
    private ScoreDAO scoreDAO = new ScoreDAOImpl();
    private GroupDAO groupDAO = new GroupDAOImpl();
    private CourseDAO courseDAO = new CourseDAOImpl();

    private CourseService courseService;

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
        System.out.println("Removing scores");
        if (scores != null && scores.size() > 0) {
            System.out.println("Scores list size: " + scores.size());
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

    public void removeStudent(Student student) {
        System.out.println("deleting student");
        if (courseService == null) {
            courseService = Services.getInstance().getCourseService();
        }
        System.out.println("Student in " + student.getCourseStudentsGroups().size() + " groups");
        int i = 0;
        List<StudentsGroup> tmp = new ArrayList<>(student.getCourseStudentsGroups());
        for (StudentsGroup studentsGroup :tmp) {
            System.out.println("iter " + i++);
            System.out.println("deleting from courses");
            courseService.removeStudentFromCourse(studentsGroup.getCourse(), student);
            System.out.println("deleting scores");
            //removeAllScoresForStudentOnCourse(student, studentsGroup.getCourse());
        }
        System.out.println("deleting");
        dao.delete(student);
        System.out.println("complete");
    }

    public List<Student> getAll() {
        return dao.findAll();
    }

    public Student getByName(String name) {
        return dao.findByName(name);
    }

}
