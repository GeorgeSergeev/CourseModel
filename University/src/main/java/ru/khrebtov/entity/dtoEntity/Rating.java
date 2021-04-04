package ru.khrebtov.entity.dtoEntity;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "study_course_id")
    private Long studyCourseId;
    @Column(name = "rating")
    private Integer rating;

    public Rating() {
    }

    public Long getStudyCourseId() {
        return studyCourseId;
    }

    public void setStudyCourseId(Long studyCourseId) {
        this.studyCourseId = studyCourseId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
