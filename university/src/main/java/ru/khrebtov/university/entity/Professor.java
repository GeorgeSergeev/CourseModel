package ru.khrebtov.university.entity;


import ru.khrebtov.university.entity.dtoEntity.DtoProfessor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "professors")
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    @NotNull
    private String name;
    @Column
    private String address;
    @Column
    @NotNull
    private String phone;
    @Column
    private Float payment;
    @ManyToMany(mappedBy = "professors")
    @Transient
    private List<Course> course;


    public Professor() {
    }

    public Professor(Long id, String name, String address, String phone, Float payment) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.payment = payment;
    }

    public Professor(DtoProfessor professor) {
        this(professor.getId(), professor.getName(), professor.getAddress(), professor.getPhone(), professor.getPayment());
        if(professor.getCourse() != null){
            professor.getCourse().forEach(c -> course.add(new Course(c)));
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

    public List<Course> getCourse() {
        return course;
    }

    public void setCourse(List<Course> course) {
        this.course = course;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Professor professor = (Professor) o;
        return id.equals(professor.id) && name.equals(professor.name) && address.equals(professor.address) && phone.equals(professor.phone) && payment.equals(professor.payment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, payment);
    }
}
