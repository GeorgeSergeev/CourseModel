package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@JsonAutoDetect
@Data
@Entity
@Table(name = "course_scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "score_id")
    private int id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course")
    private Course course;

    @Column (name = "score")
    private int score;

    public Score() {
    }

    public Score(Student student, Course course, int score) {
        this.student = student;
        this.course = course;
        this.score = score;
    }

}
