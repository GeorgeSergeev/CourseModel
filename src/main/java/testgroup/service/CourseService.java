package testgroup.service;

import testgroup.model.Course;
import testgroup.model.Student;

import java.util.List;

public interface CourseService {
    List<Course> allCourses();
    Course getByNumber(int courseID);
    void addStudent(Student student);
//    void delStudent();
//
//    void getCourseAverageScores(Student student);
//    void getFinalScore();
}
