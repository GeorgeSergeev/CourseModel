package ru.softlab.coursemodel.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "professors")
public class Professor extends BaseEntity {

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private Float payment;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
}
