package testgroup.dao;

import org.springframework.stereotype.Repository;
import testgroup.model.Course;
import testgroup.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Integer.getInteger;

@Repository
public class PersonDAOImpl implements PersonDAO {

    private static final AtomicInteger  studentID = new AtomicInteger(1);
    private static Map<Integer, Student> students = new HashMap<>();
//    private static Map<Integer, Lecturer> lecturer = new HashMap<>();

    private static List<Student> studentsList = new ArrayList<>();

    public PersonDAOImpl(){
        fill();
    }

    private void studentsList() {
        CourseDAO courseDAO = new CourseDAOImpl();
        Map<Integer, Course> courses = courseDAO.getCourses();

        Student student1 = new Student();
        student1.setName("Amy Lee");
        student1.setAddress("New-York");
        student1.setPhone("111222");
        student1.setNumgradebook(getInteger("001"));
        student1.setAverageScore(3.8f);
        List<Course> listenCourses1 = new ArrayList<>();
        listenCourses1.add(courses.get(100));
        listenCourses1.add(courses.get(101));
        student1.setListenedCourses(listenCourses1);

        Student student2 = new Student();
        student2.setName("John Week");
        student2.setAddress("Odessa");
        student2.setPhone("222333");
        student2.setNumgradebook(getInteger("002"));
        student2.setAverageScore(3.5f);
        List<Course> listenCourses2 = new ArrayList<>();
        listenCourses2.add(courses.get(101));
        listenCourses2.add(courses.get(102));
        student2.setListenedCourses(listenCourses2);

        Student student3 = new Student();
        student3.setName("John Deer");
        student3.setAddress("Washington dc");
        student3.setPhone("333444");
        student3.setNumgradebook(getInteger("003"));
        student3.setAverageScore(3.0f);
        List<Course> listenCourses3 = new ArrayList<>();
        listenCourses3.add(courses.get(102));
        student3.setListenedCourses(listenCourses3);

        Student student4 = new Student();
        student4.setName("Su Wang Gong");
        student4.setAddress("Korea");
        student4.setPhone("888888");
        student4.setNumgradebook(getInteger("004"));
        student4.setAverageScore(5.0f);
        List<Course> listenCourses4 = new ArrayList<>();
        listenCourses4.add(courses.get(100));
        student4.setListenedCourses(listenCourses4);


        studentsList.add(student1);
        studentsList.add(student2);
        studentsList.add(student3);
        studentsList.add(student4);

        for (Student student:studentsList){
            addStudent(student);
        }
    }

    private void fill() {
        studentsList();
    }

    private void addStudent(Student student){
        student.setStudentID(studentID.getAndIncrement());
        students.put(student.getStudentID(), student);
    }

    @Override
    public Student studentByID(int studentID){
        return students.get(studentID);
    }

    public void addNewStudent(Student student){
        addStudent(student);
    }

    @Override
    public void enrollCourse(int studentID, Course course){
        CourseDAO courseDAO = new CourseDAOImpl();
        Map<Integer, Course> courses = courseDAO.getCourses();



        //////////////////////////////////////////////
    }

    @Override
    public List<Student> allStudents() {
        return new ArrayList<>(students.values());
    }


//    List<Lecturer> allLecturers();


}
