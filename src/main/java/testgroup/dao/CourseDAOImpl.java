package testgroup.dao;

import org.springframework.stereotype.Repository;
import testgroup.model.Course;
import testgroup.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CourseDAOImpl implements CourseDAO {

    private static final AtomicInteger courseID = new AtomicInteger(100);
    private static Map<Integer, Course> courses = new HashMap<>();

    private static List<Course> coursesList = new ArrayList<>();

    public CourseDAOImpl(){
        fill();
    }

    private void coursesList() {
        Course geography = new Course();
        geography.setCourseName("Geography");
        geography.setCoursePrice(0.0f);

        Course literature = new Course();
        literature.setCourseName("Literature");
        literature.setCoursePrice(500.0f);

        Course physics = new Course();
        physics.setCourseName("Physics");
        physics.setCoursePrice(600.0f);

        coursesList.add(geography);
        coursesList.add(literature);
        coursesList.add(physics);

        for (Course course:coursesList){
            addNewCourse(course);
        }
    }

    private void fill() {
        coursesList();
    }

    public void addNewCourse(Course course){
        course.setCourseID(courseID.getAndIncrement());
        courses.put(course.getCourseID(), course);
    }

    @Override
    public Map<Integer, Course> getCourses(){
        return courses;
    }

    @Override
    public Course getByNumber(int ID){
        return courses.get(ID);
    }

    @Override
    public void addStudent(Student student) {

    }

    public List<Course> allCourses(){
        return new ArrayList<>(courses.values());
    }
}
