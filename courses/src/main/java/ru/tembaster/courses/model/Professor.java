package ru.tembaster.courses.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Entity
@Table(name = "professors")
public class Professor extends AbstractNamedEntity {

    @NotNull
    @Column(name = "payment")
    private Double payment;

    @JsonIgnore
    @OneToOne(mappedBy = "professor")
    private Course course;

    public Professor() {
    }

    public Professor(Integer id, String name, String address, String phone, Double payment) {
        super(id, name, address, phone);
        this.payment = payment;
    }

    public Professor(String name, String address, String phone, Double payment) {
        this(null, name, address, phone, payment);
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", payment=" + payment +
                '}';
    }
}
