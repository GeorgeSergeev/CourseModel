package ru.khrebtov.university.entity;


import ru.khrebtov.university.entity.dtoEntity.DtoCourse;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private int number;
    @Column
    private float cost;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "study_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students;

    @OneToMany(mappedBy = "course")
    @Transient
    private List<StudyCourse> studyCourses;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "course_professor",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "professors_id")
    )
    private List<Professor> professors;


    public Course() {
    }

    public Course(Long id, String name, int number, float cost) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.cost = cost;
    }

    public Course(DtoCourse course) {
        this(course.getId(), course.getName(), course.getNumber(), course.getCost());

        if (course.getStudyCourses() != null) {
            this.studyCourses = new ArrayList<>();
            course.getStudyCourses().forEach(studyCourse -> studyCourses.add(new StudyCourse(studyCourse)));
        }

        if (course.getStudents() != null) {
            this.students = new ArrayList<>();
            course.getStudents().forEach(student -> students.add(new Student(student)));
        }

        if (course.getProfessors() != null) {
            this.professors = new ArrayList<>();
            course.getProfessors().forEach(p -> professors.add(new Professor(p)));
        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<StudyCourse> getStudyCourses() {
        return studyCourses;
    }

    public void setStudyCourses(List<StudyCourse> studyCourses) {
        this.studyCourses = studyCourses;
    }

    public List<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(List<Professor> professors) {
        this.professors = professors;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", cost=" + cost +
                '}';
    }
}
