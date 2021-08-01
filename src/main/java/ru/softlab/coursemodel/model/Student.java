package ru.softlab.coursemodel.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @Column
    private String email;

    @Column
    private Integer recordBookNumber;

    @Lazy
    @Column(name = "avg_performance")
    private Float averagePerformance;

    @Lazy
    @ManyToMany
    @JoinTable(
            name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id"),
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
        private String email;
        private Integer recordBookNumber;
        private Float avgPerformance;
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

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder recordBookNumber(Integer recordBookNumber) {
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Builder avgPerformance(Float avgPerformance) {
            this.avgPerformance = avgPerformance;
            return this;
        }

        public Builder courses(Collection<Course> courses) {
            this.courses = courses;
            return this;
        }

        public Student build() {
            Student student = new Student();
            student.setId(id);
            student.setVersion(version);
            student.setName(name);
            student.setAddress(address);
            student.setPhone(phone);
            student.setEmail(email);
            student.setRecordBookNumber(recordBookNumber);
            student.setAveragePerformance(avgPerformance);
            student.setCourses(courses);
            return student;
        }
    }
}
