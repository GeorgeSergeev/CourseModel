package max.mustafin.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
public class Student {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int scoreBookNumber;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;
    @NotNull
    @Column(name = "email")
    private String email;
    @NotNull
    @Column(name = "avg_score")
    private float averageScore;
    @Column(name = "nfc")
    private int numFinishedCourses;
    @Column(name = "ts")
    private float totalScore;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Course> courseList = new ArrayList<>();
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CourseProgress courseProgress;
    @OneToOne(fetch =  FetchType.EAGER, cascade = CascadeType.ALL)
    private History history;

    public Student() {
    }

    public Student(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public float getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(float totalScore) {
        this.totalScore = totalScore;
    }

    public int getNumFinishedCourses() {
        return numFinishedCourses;
    }

    public void setNumFinishedCourses(int numFinishedCourses) {
        this.numFinishedCourses = numFinishedCourses;
    }

    public History getHistory() {
        return history;
    }

    public void setHistory(History history) {
        this.history = history;
    }

    public int getScoreBookNumber() {
        return scoreBookNumber;
    }

    public void setScoreBookNumber(int scoreBookNumber) {
        this.scoreBookNumber = scoreBookNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public float getAverageScore() {
        return averageScore;
    }

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }
    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public CourseProgress getCourseProgress() {
        return courseProgress;
    }

    public void setCourseProgress(CourseProgress courseProgress) {
        this.courseProgress = courseProgress;
    }

    @Override
    public String toString() {
        return "Student{" +
                "scoreBookNumber=" + scoreBookNumber +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", averageScore=" + averageScore +
                '}';
    }
}
