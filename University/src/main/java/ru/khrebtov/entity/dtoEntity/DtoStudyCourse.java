package ru.khrebtov.entity.dtoEntity;

import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Student;
import ru.khrebtov.entity.StudyCourse;

import java.util.List;

public class DtoStudyCourse {

    private Long id;
    private List<Integer> rating;

    private DtoStudent student;
    private DtoCourse course;

    public DtoStudyCourse() {
    }

    public DtoStudyCourse(Long id, List<Integer> rating, DtoStudent student, DtoCourse course) {
        this.id = id;
        this.rating = rating;
        this.student = student;
        this.course = course;
    }

    public DtoStudyCourse(Long id, List<Integer> rating) {
        this.id = id;
        this.rating = rating;
    }

    public DtoStudyCourse(Long id, List<Integer> rating, DtoCourse course) {
        this(id, rating);
        this.course = course;
    }

    public DtoStudyCourse(StudyCourse studyCourse) {
        this.id = studyCourse.getId();
        this.rating = studyCourse.getRating();

        Student student = studyCourse.getStudent();
        if (student != null) {
            this.student = new DtoStudent(student.getId(), student.getName(), student.getAddress(), student.getAddress(),
                    student.getEmail(), student.getRecordBook(), student.getProgress());
        }
        Course course = studyCourse.getCourse();
        if (course != null) {
            this.course = new DtoCourse(course.getId(), course.getName(), course.getNumber(), course.getCost());
        }
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

    public DtoStudent getStudent() {
        return student;
    }

    public void setStudent(DtoStudent student) {
        this.student = student;
    }

    public DtoCourse getCourse() {
        return course;
    }

    public void setCourse(DtoCourse course) {
        this.course = course;
    }
}
