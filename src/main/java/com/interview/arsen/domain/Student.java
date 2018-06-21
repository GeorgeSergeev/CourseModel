package com.interview.arsen.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Student.
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "idstudentbook")
    private Long idstudentbook;

    @Column(name = "avaragemark")
    private Float avaragemark;

    @ManyToMany
    @JoinTable(name = "student_course",
               joinColumns = @JoinColumn(name="students_id", referencedColumnName="id"),
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

    public Student name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public Student address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public Student phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public Student email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdstudentbook() {
        return idstudentbook;
    }

    public Student idstudentbook(Long idstudentbook) {
        this.idstudentbook = idstudentbook;
        return this;
    }

    public void setIdstudentbook(Long idstudentbook) {
        this.idstudentbook = idstudentbook;
    }

    public Float getAvaragemark() {
        return avaragemark;
    }

    public Student avaragemark(Float avaragemark) {
        this.avaragemark = avaragemark;
        return this;
    }

    public void setAvaragemark(Float avaragemark) {
        this.avaragemark = avaragemark;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public Student courses(Set<Course> courses) {
        this.courses = courses;
        return this;
    }

    public Student addCourse(Course course) {
        this.courses.add(course);
        course.getStudents().add(this);
        return this;
    }

    public Student removeCourse(Course course) {
        this.courses.remove(course);
        course.getStudents().remove(this);
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
        Student student = (Student) o;
        if (student.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), student.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Student{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", idstudentbook=" + getIdstudentbook() +
            ", avaragemark=" + getAvaragemark() +
            "}";
    }
}
