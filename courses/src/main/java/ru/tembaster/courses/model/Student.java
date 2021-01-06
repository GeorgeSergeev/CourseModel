package ru.tembaster.courses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends AbstractNamedEntity {

    @Email
    @NotBlank
    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "student_number")
    private Integer studentNumber;

    @NotNull
    @Column(name = "avg_performance")
    private Float avgPerformance;

    @JsonIgnore
    @ManyToMany(mappedBy = "students")
    private Set<Course> course;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    private List<CourseProgress> courseProgress;

    public Student() {
    }

    public Student(Integer id, String name, String address, String phone, String email, Integer studentNumber, Float avgPerformance) {
        super(id, name, address, phone);
        this.email = email;
        this.studentNumber = studentNumber;
        this.avgPerformance = avgPerformance;
    }

    public Student(String name, String address, String phone, String email, Integer studentNumber, Float avgPerformance) {
        this(null, name, address, phone, email, studentNumber, avgPerformance);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", studentNumber=" + studentNumber +
                ", avgPerformance=" + avgPerformance +
                '}';
    }
}
