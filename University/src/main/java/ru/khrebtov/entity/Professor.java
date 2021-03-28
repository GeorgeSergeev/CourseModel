package ru.khrebtov.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "professors")
@NamedQueries({
        @NamedQuery(name = "findAllProfessors", query = "from Professor "),
        @NamedQuery(name = "countAllProfessors", query = "select count(*) from Professor "),
        @NamedQuery(name = "deleteProfessorsById", query = "delete from Professor p where p.id = :id"),
        @NamedQuery(name = "findProfessorById", query = "from Professor p where p.id = :id")
})
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
    private Set<Course> course;


    public Professor() {
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

    public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> course) {
        this.course = course;
    }
}
