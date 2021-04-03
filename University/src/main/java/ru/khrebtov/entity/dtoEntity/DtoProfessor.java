package ru.khrebtov.entity.dtoEntity;

import ru.khrebtov.entity.Professor;

import java.util.HashSet;
import java.util.Set;

public class DtoProfessor {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Float payment;
    private Set<DtoCourse> course;

    public DtoProfessor() {
    }

    public DtoProfessor(Long id, String name, String address, String phone, Float payment) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.payment = payment;
    }

    public DtoProfessor(Professor professor) {
        this(professor.getId(), professor.getName(), professor.getAddress(), professor.getPhone(), professor.getPayment());
        if (professor.getCourse() != null) {
            this.course = new HashSet<>();
            professor.getCourse().forEach(c -> this.course.add(new DtoCourse(c.getId(), c.getName(), c.getNumber(), c.getCost())));
        }
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

    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }

    public Set<DtoCourse> getCourse() {
        return course;
    }

    public void setCourse(Set<DtoCourse> course) {
        this.course = course;
    }
}
