package testgroup.model;

import java.util.List;

public class Course {

    private String courseName;
    private int courseNumber;
    private float coursePrice;

    private List<Student> ListenStudents;
    private boolean selfStudy;
    private Lecturer lecturer;

    public String getCourseName() {
        return courseName;
    }

    public int getCourseID() {
        return courseNumber;
    }

    public float getCoursePrice() {
        return coursePrice;
    }

    public boolean isSelfStudy() {
        return selfStudy;
    }

    public List<Student> getListenStudents() {
        return ListenStudents;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseID(int courseNumber) {
        this.courseNumber = courseNumber;
    }

    public void setCoursePrice(float coursePrice) {
        this.coursePrice = coursePrice;
    }

    public void setSelfStudy(boolean selfStudy) {
        this.selfStudy = selfStudy;
    }

    public void setListenStudents(List<Student> listenStudents) {
        ListenStudents = listenStudents;
    }

    @Override
    public String toString() {
        return courseName;
    }
}
