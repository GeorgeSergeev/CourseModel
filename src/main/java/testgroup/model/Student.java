package testgroup.model;

import java.util.List;

public class Student extends Person{
    private int studentID;
    private String email;
    private Integer numgradebook;
    private float averageScore;
    private List<Course> ListenedCourses;

    public int getStudentID() {
        return studentID;
    }

    public Integer getNumgradebook() {
        return numgradebook;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public String getEmail() {
        return email;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public List<Course> getListenedCourses() {
        return ListenedCourses;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumgradebook(Integer numgradebook) {
        this.numgradebook = numgradebook;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    public void setListenedCourses(List<Course> listenedCourses) {
        ListenedCourses = listenedCourses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + super.getName() + '\'' +
                ", numgradebook=" + numgradebook +
                ", averageScore=" + averageScore +
                '}';
    }
}
