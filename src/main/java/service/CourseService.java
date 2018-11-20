package service;

import dao.CourseDAO;
import dao.CourseDAOImpl;
import dao.GroupDAO;
import dao.GroupDAOImpl;
import lombok.NoArgsConstructor;
import model.*;

@NoArgsConstructor
public class CourseService {

    private CourseDAO dao = new CourseDAOImpl();
    private GroupDAO groupDAO = new GroupDAOImpl();

    public void addCourse(Course course) {
        dao.save(course);
    }

    public void addStudentToCourse(Course course, Student student) {
        StudentsGroup studentsGroup = new StudentsGroup(course, student);
        groupDAO.save(studentsGroup);
    }

    public void changeStudentStatusOnCourse(Course course, Student student, StudentStatus status) {
        StudentsGroup studentsGroup = groupDAO.getGroupByStudentAndCourse(student, course);
        studentsGroup.setStatus(status);
        groupDAO.update(studentsGroup);
    }

    public void setProfessorForCourse(Course course, Professor professor) {
        course.setProfessor(professor);
        dao.update(course);
    }

}
