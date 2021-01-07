package ru.tembaster.courses.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    private Double avgPerformance;

    @OneToMany(mappedBy = "student")
    private Set<CourseProgress> courses;

    public Student() {
    }

    public Student(Integer id, String name, String address, String phone, String email, Integer studentNumber) {
        super(id, name, address, phone);
        this.email = email;
        this.studentNumber = studentNumber;
    }

    public Student(String name, String address, String phone, String email, Integer studentNumber) {
        this(null, name, address, phone, email, studentNumber);
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
