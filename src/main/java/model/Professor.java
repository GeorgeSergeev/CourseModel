package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect
@Data
@Entity
@Table(name = "teachers")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "teacher_id")
    private int id;

    @Column (name = "name")
    private String name;

    @Column (name = "address")
    private String address;

    @Column (name = "phone")
    private String phone;

    @Column (name = "salary")
    private float salary;

    @JsonIgnore
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL)
    private List<Course> courses = new ArrayList<>();

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }

    public Professor() {
    }

    public Professor(Professor professor) {
        this.name = professor.getName();
        this.address = professor.getAddress();
        this.phone = professor.getPhone();
        this.salary = professor.getSalary();
    }

    public Professor(String name, String address, String phone, float salary) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }
}
