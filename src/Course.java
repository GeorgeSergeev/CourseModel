
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by TheEndl on 01.05.2017.
 */


public class Course {
    private String courseName;
    private int courseNumber;
    private float courseCost;
    private Teacher teacher;

    public Course(String courseName, int courseNumber, float courseCost) {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.courseCost = courseCost;
    }

    public Course(String courseName, int courseNumber, float courseCost, Teacher teacher) {
        this.courseName = courseName;
        this.courseNumber = courseNumber;
        this.courseCost = courseCost;
        this.teacher = teacher;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public float getCourseCost() {
        return courseCost;
    }

    public void setCourseCost(float courseCost) {
        this.courseCost = courseCost;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (courseNumber != course.courseNumber) return false;
        if (Float.compare(course.courseCost, courseCost) != 0) return false;
        return courseName.equals(course.courseName);
    }

    @Override
    public int hashCode() {
        int result = courseName.hashCode();
        result = 31 * result + courseNumber;
        result = 31 * result + (courseCost != +0.0f ? Float.floatToIntBits(courseCost) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseName='" + courseName + '\'' +
                ", courseNumber=" + courseNumber +
                ", courseCost=" + courseCost +
                '}';
    }
}
