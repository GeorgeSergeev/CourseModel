package model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "course_id")
    private int id;

    @Column (name = "name")
    private String name;

    @Column (name = "cost")
    private float cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_teacher")
    private Professor professor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentsGroup> courseStudentsGroups;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Score> scores;

    public Course() {
    }

    public Course(String name, float cost) {
        this.name = name;
        this.cost = cost;
    }
}
