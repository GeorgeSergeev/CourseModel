package com.interview.arsen.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Course.
 */
@Entity
@Table(name = "course")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "jhi_number")
    private Integer number;

    @Column(name = "jhi_cost")
    private Float cost;

    @OneToMany(mappedBy = "course")
    @JsonIgnore
    private Set<Subject> subjects = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private Set<Student> students = new HashSet<>();

    @ManyToMany(mappedBy = "courses")
    @JsonIgnore
    private Set<Teacher> teachers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Course name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public Course number(Integer number) {
        this.number = number;
        return this;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Float getCost() {
        return cost;
    }

    public Course cost(Float cost) {
        this.cost = cost;
        return this;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public Course subjects(Set<Subject> subjects) {
        this.subjects = subjects;
        return this;
    }

    public Course addSubject(Subject subject) {
        this.subjects.add(subject);
        subject.setCourse(this);
        return this;
    }

    public Course removeSubject(Subject subject) {
        this.subjects.remove(subject);
        subject.setCourse(null);
        return this;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Course students(Set<Student> students) {
        this.students = students;
        return this;
    }

    public Course addStudent(Student student) {
        this.students.add(student);
        student.getCourses().add(this);
        return this;
    }

    public Course removeStudent(Student student) {
        this.students.remove(student);
        student.getCourses().remove(this);
        return this;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public Course teachers(Set<Teacher> teachers) {
        this.teachers = teachers;
        return this;
    }

    public Course addTeacher(Teacher teacher) {
        this.teachers.add(teacher);
        teacher.getCourses().add(this);
        return this;
    }

    public Course removeTeacher(Teacher teacher) {
        this.teachers.remove(teacher);
        teacher.getCourses().remove(this);
        return this;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Course course = (Course) o;
        if (course.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), course.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Course{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", number=" + getNumber() +
            ", cost=" + getCost() +
            "}";
    }
}
