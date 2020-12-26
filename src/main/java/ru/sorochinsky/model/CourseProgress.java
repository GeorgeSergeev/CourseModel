package ru.sorochinsky.model;

import javax.persistence.*;
import java.util.List;

/**
 * Simple JavaBean object that represents role of {@link CourseProgress}
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

@Entity // This tells Hibernate to make a table out of this class
public class CourseProgress {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private boolean completed;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Student student;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Course course;

    @ElementCollection
    private List<Integer> points;

    private Integer finalPoint;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public Integer getFinalPoint() {
        return finalPoint;
    }

    public void setFinalPoint(Integer finalPoint) {
        this.finalPoint = finalPoint;
    }

    public List<Integer> getPoints() {
        return points;
    }

    public void setPoints(List<Integer> points) {
        this.points = points;
    }
}
