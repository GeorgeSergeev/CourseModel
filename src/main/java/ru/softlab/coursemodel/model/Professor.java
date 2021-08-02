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

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private Integer version;
        private String name;
        private String address;
        private String phone;
        private Float payment;
        private Course course;

        private Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder version(Integer version) {
            this.version = version;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder payment(Float payment) {
            this.payment = payment;
            return this;
        }

        public Builder course(Course course) {
            this.course = course;
            return this;
        }

        public Professor build() {
            Professor professor = new Professor();
            professor.setId(id);
            professor.setVersion(version);
            professor.setName(name);
            professor.setAddress(address);
            professor.setPhone(phone);
            professor.setPayment(payment);
            professor.setCourse(course);
            return professor;
        }
    }
}
