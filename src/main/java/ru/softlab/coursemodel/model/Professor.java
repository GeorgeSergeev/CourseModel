package ru.softlab.coursemodel.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Collection;

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

    @Lazy
    @ManyToMany
    @JoinTable(
            name = "professors_courses",
            joinColumns = @JoinColumn(name = "professor_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private Collection<Course> courses;

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
        private Collection<Course> courses;

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

        public Builder courses(Collection<Course> courses) {
            this.courses = courses;
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
            professor.setCourses(courses);
            return professor;
        }
    }
}
