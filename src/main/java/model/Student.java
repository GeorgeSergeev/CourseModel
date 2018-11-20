package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "student_id")
    private int id;

    @Column (name = "name")
    private String name;

    @Column (name = "address")
    private String address;

    @Column (name = "phone")
    private String phone;

    @Column (name = "email")
    private String email;

    @Column (name = "grade_book_num")
    private int gradeBookNum;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentsGroup> courseStudentsGroups;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Score> scores;

    public Student() {
    }

    public Student(String name, String address, String phone, String email, int gradeBookNum) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.gradeBookNum = gradeBookNum;
    }
}
