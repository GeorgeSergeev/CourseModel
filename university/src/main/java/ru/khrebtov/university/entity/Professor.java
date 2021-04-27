package ru.khrebtov.university.entity;


import ru.khrebtov.university.entity.dtoEntity.DtoProfessor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "professors")
@NamedQueries({
        @NamedQuery(name = "findAllProfessors", query = "select p from Professor p"),
        @NamedQuery(name = "countAllProfessors", query = "select count(p) from Professor p "),
        @NamedQuery(name = "deleteProfessorsById", query = "delete from Professor p where p.id = :id"),
        @NamedQuery(name = "findProfessorById", query = "select p from Professor p where p.id = :id"),
        @NamedQuery(name = "getProfessorCourse", query = "select c from Course c left join CourseProfessor cp" +
                " on c.id=cp.courseId where cp.professorsId = :professorId")
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

    public Professor(DtoProfessor professor) {
        this.id = professor.getId();
        this.name = professor.getName();
        this.address = professor.getAddress();
        this.phone = professor.getPhone();
        this.payment = professor.getPayment();
        this.course = new HashSet<>();
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

    public Set<Course> getCourse() {
        return course;
    }

    public void setCourse(Set<Course> course) {
        this.course = course;
    }
}
