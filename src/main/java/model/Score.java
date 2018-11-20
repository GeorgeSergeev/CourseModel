package model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "course_scores")
public class Score {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "score_id")
    private int id;

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
}
