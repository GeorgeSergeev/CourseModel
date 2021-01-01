package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Cascade;

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

    public void removeFromGroup(StudentsGroup group) {
        courseStudentsGroups.remove(group);
        List<Score> tmp = new ArrayList<>(scores);
        for (Score score :tmp) {
            if (score.getCourse().getId() == group.getCourse().getId() && score.getStudent().getId() == group.getStudent().getId()) {
                scores.remove(score);
            }
        }
    }

    public void addSCore(Score score) {
        scores.add(score);
    }

    public void removeScore(Score score) {
        scores.remove(score);
    }

    public Course() {
    }

    public Course(Course course) {
        this.name = course.getName();
        this.cost = course.getCost();
    }

    public Course(String name, float cost) {
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return  name + " (" + cost + ")";
    }
}
