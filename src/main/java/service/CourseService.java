package service;

import dao.*;
import lombok.NoArgsConstructor;
import model.*;
import util.Services;

import java.util.List;

@NoArgsConstructor
public class CourseService {

    private CourseDAO dao = new CourseDAOImpl();
    private GroupDAO groupDAO = new GroupDAOImpl();
    private StudentDAO studentDAO = new StudentDAOImpl();

    private StudentService studentService;

    public Course getById(int id) {
        return dao.findById(id);
    }

    public Course getByName(String name) {
        return dao.findByName(name);
    }

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

    public void removeStudentFromCourse(Course course, Student student) {
        if (studentService == null) {
            studentService = Services.getInstance().getStudentService();
        }
        System.out.println("..." + course.getId() + " " + student.getId());
        StudentsGroup studentsGroup = groupDAO.getGroupByStudentAndCourse(student, course);
        if (studentsGroup != null) {
            student.removeFromGroup(studentsGroup);
            course.removeFromGroup(studentsGroup);
            System.out.println("..");
            groupDAO.delete(studentsGroup);
            System.out.println(".");
        } else {
            System.out.println("Student doesn't exist on this course");
        }
        //studentService.removeAllScoresForStudentOnCourse(student, course);
    }

    public void changeStudentStatusOnCourse(Course course, Student student, StudentStatus status) {
        StudentsGroup studentsGroup = groupDAO.getGroupByStudentAndCourse(student, course);
        studentsGroup.setStatus(status);
        groupDAO.update(studentsGroup);
    }

    public void setProfessorForCourse(Course course, Professor professor) {
        course.setProfessor(professor);
        professor.addCourse(course);
        dao.update(course);
    }

    public void removeProfessorFromCourse(Course course) {
        Professor professor = course.getProfessor();
        professor.removeCourse(course);
        course.setProfessor(null);
        dao.update(course);
    }

    public List<Course> getAll() {
        return dao.findAll();
    }

}
