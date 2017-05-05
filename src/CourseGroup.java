import java.util.ArrayList;

/**
 * Created by TheEndl on 05.05.2017.
 */
public class CourseGroup extends Course {
    ArrayList<Student> list = new ArrayList<>();


    public CourseGroup(String courseName, int courseNumber, float courseCost) {
        super(courseName, courseNumber, courseCost);
    }

    public CourseGroup(String courseName, int courseNumber, float courseCost, Teacher teacher) {
        super(courseName, courseNumber, courseCost, teacher);
    }

    public void addStudent(Student student)
    {
        list.add(student);
    }
}
