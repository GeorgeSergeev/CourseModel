package service;

import dao.*;
import lombok.NoArgsConstructor;
import model.*;

@NoArgsConstructor
public class CourseService {

    private CourseDAO dao = new CourseDAOImpl();
    private GroupDAO groupDAO = new GroupDAOImpl();
    private StudentDAO studentDAO = new StudentDAOImpl();

    public void addCourse(Course course) {
        if (dao.findById(course.getId()) == null) {
            dao.save(course);
        } else {
            System.out.println("Course already exist");
        }
    }

    public void addStudentToCourse(Course course, Student student) {
        if (groupDAO.getGroupByStudentAndCourse(student, course) == null) {
            StudentsGroup studentsGroup = new StudentsGroup(course, student);
            student.addToGroup(studentsGroup);
            course.addToGroup(studentsGroup);
            groupDAO.save(studentsGroup);
            dao.update(course);
            studentDAO.update(student);
        } else {
            System.out.println("Student already joined this group");
        }
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
