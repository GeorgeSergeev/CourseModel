package ru.khrebtov.entity.dtoEntity;


import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Student;

import java.util.HashSet;
import java.util.Set;

public class DtoStudent {

    private Long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Integer recordBook;

    private Float progress;
    private Set<DtoCourse> courses;
    private Set<DtoStudyCourse> studyCourses;

    public DtoStudent() {
    }

    public DtoStudent(Long id, String name, String address, String phone, String email, Integer recordBook, Float progress) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.recordBook = recordBook;
        this.progress = progress;
    }

    public DtoStudent(Student student) {
        this(student.getId(), student.getName(), student.getAddress(), student.getPhone(), student.getEmail(),
                student.getRecordBook(), student.getProgress());
        this.studyCourses = new HashSet<>();

        student.getStudyCourses().forEach(sc -> {
            Course course = sc.getCourse();
            Set<DtoProfessor> professors = new HashSet<>();
            course.getProfessors().forEach(p -> professors.add(new DtoProfessor(p.getId(), p.getName(),
                    p.getAddress(), p.getPhone(), p.getPayment())));
            studyCourses.add(new DtoStudyCourse(sc.getId(), sc.getRating(), new DtoCourse(course.getId(),
                    course.getName(), course.getNumber(), course.getCost(), professors)));
        });
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

    public Float getProgress() {
        return progress;
    }

    public void setProgress(Float progress) {
        this.progress = progress;
    }

    public Set<DtoCourse> getCourses() {
        return courses;
    }

    public void setCourses(Set<DtoCourse> courses) {
        this.courses = courses;
    }

    public Set<DtoStudyCourse> getStudyCourses() {
        return studyCourses;
    }

    public void setStudyCourses(Set<DtoStudyCourse> studyCourses) {
        this.studyCourses = studyCourses;
    }
}
