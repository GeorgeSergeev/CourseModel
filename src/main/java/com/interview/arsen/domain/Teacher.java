package com.interview.arsen.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Teacher.
 */
@Entity
@Table(name = "teacher")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phonenumber")
    private String phonenumber;

    @Column(name = "salary")
    private Float salary;

    @ManyToMany
    @JoinTable(name = "teacher_course",
               joinColumns = @JoinColumn(name="teachers_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="courses_id", referencedColumnName="id"))
    private Set<Course> courses = new HashSet<>();

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

    public Teacher name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Teacher address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public Teacher phonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
        return this;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Float getSalary() {
        return salary;
    }

    public Teacher salary(Float salary) {
        this.salary = salary;
        return this;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Teacher courses(Set<Course> courses) {
        this.courses = courses;
        return this;
    }

    public Teacher addCourse(Course course) {
        this.courses.add(course);
        course.getTeachers().add(this);
        return this;
    }

    public Teacher removeCourse(Course course) {
        this.courses.remove(course);
        course.getTeachers().remove(this);
        return this;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
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
        Teacher teacher = (Teacher) o;
        if (teacher.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacher.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Teacher{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", phonenumber='" + getPhonenumber() + "'" +
            ", salary=" + getSalary() +
            "}";
    }
}
