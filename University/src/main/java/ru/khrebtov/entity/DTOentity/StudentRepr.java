package ru.khrebtov.entity.DTOentity;

import ru.khrebtov.entity.Student;

import java.util.HashSet;
import java.util.Set;

public class StudentRepr {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Integer recordBook;
    private float progress;
    private Set<CourseRepr> courses;

    public StudentRepr() {
    }

    public StudentRepr(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.address = student.getAddress();
        this.phone = student.getPhone();
        this.email = student.getEmail();
        this.recordBook = student.getRecordBook();
        this.progress = student.getProgress();
        this.courses = new HashSet<>();
        student.getCourses().forEach(c -> this.courses.add(new CourseRepr(c)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRecordBook() {
        return recordBook;
    }

    public void setRecordBook(Integer recordBook) {
        this.recordBook = recordBook;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }

    public Set<CourseRepr> getCourses() {
        return courses;
    }

    public void setCourses(Set<CourseRepr> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "StudentRepr{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", recordBook=" + recordBook +
                ", progress=" + progress +
                '}';
    }
}
