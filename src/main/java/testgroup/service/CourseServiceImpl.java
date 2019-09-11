package testgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testgroup.dao.CourseDAO;
import testgroup.model.Course;
import testgroup.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {
    private CourseDAO courseDAO;

    @Autowired
    public void setCourseDAO(CourseDAO courseDAO) {
        this.courseDAO = courseDAO;
    }

    @Override
    public List<Course> allCourses(){
        Map<Integer, Course> courses = courseDAO.getCourses();
        return new ArrayList<>(courses.values());
    }

    @Override
    public Course getByNumber(int courseID) {
        return courseDAO.getByNumber(courseID);
    }

    @Override
    public void addStudent(Student student) {

    }

}
