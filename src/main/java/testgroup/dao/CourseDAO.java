package testgroup.dao;

import testgroup.model.Course;
import testgroup.model.Student;

import java.util.List;
import java.util.Map;

public interface CourseDAO {
    Course getByNumber(int ID);
    Map<Integer, Course> getCourses();
    void addStudent(Student student);
//    void deleteCource(Course course);
//    void edit(Course course);
//    Course getByCourseNumber(int id);

//    void getCourseAverageScores(Student student); //or byID
//    void getFinalScore(Student student); // or byID
}
