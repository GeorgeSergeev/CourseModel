package testgroup.service;

import testgroup.model.Course;
import testgroup.model.Lecturer;
import testgroup.model.Person;
import testgroup.model.Student;

import java.util.List;

public interface PersonService {
    List<Student> allStudents();
    Student studentByID(int studentID);

//    List<Lecturer> allLecturers();

    void enrollСourse(int studentID, Course course); // может записаться на курс
    //List<Course> ListenedCourses(Student student);
}
