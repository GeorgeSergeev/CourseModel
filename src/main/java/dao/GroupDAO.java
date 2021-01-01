package dao;

import model.Course;
import model.StudentsGroup;
import model.Student;

public interface GroupDAO extends AbstractDAO<StudentsGroup> {

    StudentsGroup getGroupByStudentAndCourse(Student student, Course course);

}
