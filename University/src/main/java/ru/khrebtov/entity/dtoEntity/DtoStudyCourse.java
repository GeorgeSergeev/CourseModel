package ru.khrebtov.entity.dtoEntity;

import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Student;
import ru.khrebtov.entity.StudyCourse;

import java.util.List;

public class DtoStudyCourse {

    private Long id;
    private List<Integer> rating;
    private Float finalRating;
    private Student student;
    private Course course;

    public DtoStudyCourse() {
    }

    public DtoStudyCourse(StudyCourse studyCourse, List<Integer> rating) {
        this.id = studyCourse.getId();
        this.rating = rating;
        this.student = studyCourse.getStudent();
        this.course = studyCourse.getCourse();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Integer> getRating() {
        return rating;
    }

    public void setRating(List<Integer> rating) {
        this.rating = rating;
    }

    public Float getFinalRating() {
        return finalRating;
    }

    public void setFinalRating(Float finalRating) {
        this.finalRating = finalRating;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
