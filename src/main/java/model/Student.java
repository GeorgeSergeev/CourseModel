package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import service.StudentService;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect
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

    @JsonUnwrapped
    @JsonManagedReference
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentsGroup> courseStudentsGroups = new ArrayList<>();

    @JsonUnwrapped
    @JsonManagedReference
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Score> scores = new ArrayList<>();

    public void addToGroup(StudentsGroup group) {
        courseStudentsGroups.add(group);
    }

    public void addSCore(Score score) {
        scores.add(score);
    }

    public Student() {
    }

    public Student(String name, String address, String phone, String email, int gradeBookNum) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.gradeBookNum = gradeBookNum;
    }

    public float calculateAverageScore() {
        float result = 0f;
        int i = 0;
        for (Score score :scores) {
            i += score.getScore();
        }
        result = 1.0f * i / scores.size();
        return result;
    }

}
