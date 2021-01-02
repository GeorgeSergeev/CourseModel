package ru.tembaster.courses.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "students")
public class Student extends AbstractEntity {

    @Email
    @NotNull
    @Column(name = "email")
    String email;

    @NotNull
    @Column(name = "grade_book_id")
    Integer gradeBookId;

    @NotNull
    @Column(name = "avg_perf")
    Float avgPerfomance;

    public Student() {
    }

    public Student(Integer id, String name, String address, String phone,String email, Integer gradeBookId, Float avgPerfomance) {
        super(id, name, address, phone);
        this.email = email;
        this.gradeBookId = gradeBookId;
        this.avgPerfomance = avgPerfomance;
    }

    public Student(String name, String address, String phone,String email, Integer gradeBookId, Float avgPerfomance) {
        this(null, name, address, phone, email, gradeBookId, avgPerfomance);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", gradeBookId=" + gradeBookId +
                ", avgPerfomance=" + avgPerfomance +
                '}';
    }
}
