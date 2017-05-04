

/**
 * Created by TheEndl on 04.05.2017.
 */


public class CourseWithTeacher extends Course {

    public CourseWithTeacher(String courseName, int courseNumber, float courseCost, Teacher teacher) {
        super(courseName, courseNumber, courseCost, teacher);
    }
    @Override
    public String toString() {
        return "Course{" +
                "courseName:'" + courseName + '\'' +
                ", course Number:" + courseNumber +
                ", course Cost:" + courseCost +
                ", Teacher:" + teacher.getName() +
                '}';
    }

}
