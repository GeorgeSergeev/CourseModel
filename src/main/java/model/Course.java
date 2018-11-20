package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect
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

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentsGroup> courseStudentsGroups = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Score> scores = new ArrayList<>();

    public void addToGroup(StudentsGroup group) {
        courseStudentsGroups.add(group);
    }

    public void addSCore(Score score) {
        scores.add(score);
    }

    public Course() {
    }

    public Course(String name, float cost) {
        this.name = name;
        this.cost = cost;
    }
}
