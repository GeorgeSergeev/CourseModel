package testgroup.dao;

import testgroup.model.Course;
import testgroup.model.Lecturer;
import testgroup.model.Student;

import java.util.List;

public interface PersonDAO
{
    Student studentByID(int studentID);
    List<Student> allStudents();
    void enrollCourse(int studentID, Course course);
//    List<Lecturer> allLecturers();
}
