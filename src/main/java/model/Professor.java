package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Course> courses;

    public Professor() {
    }

    public Professor(String name, String address, String phone, float salary) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }
}
