package org.bajiepka.courseApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.persistence.*;

import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Student {

    private @Id @GeneratedValue Long id;
    private @Version int version;
    @NotNull
    private String name;
    private String address;
    private String phone;

    private Integer gradeBook;
    private float averageProgress;

    @PostConstruct
    private void fillGradebookNumber(){
        gradeBook = gradeBook + Math.toIntExact(id);
    }

    @OneToMany(targetEntity = CourseProgress.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name="students_progress", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "progress_id")
    )
    private List<CourseProgress> progressList;

    public Student() {
    }

    public Student(String name, String address, String phone, Integer gradeBook) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.gradeBook = gradeBook;
    }

    public void finishedCourses() {

    }

    @Override
    public String toString() {
        return getName();
    }

    public void fill(Student copy) {
        this.version++;
        this.name = copy.getName();
        this.phone = copy.getPhone();
        this.address = copy.getAddress();
        this.averageProgress = copy.getAverageProgress();
        this.gradeBook = copy.getGradeBook();
    }
}
