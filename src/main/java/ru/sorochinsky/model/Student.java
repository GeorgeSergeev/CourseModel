package ru.sorochinsky.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Simple JavaBean object that represents role of {@link Student}
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

@Entity // This tells Hibernate to make a table out of this class
public class Student {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String address;

    @NotNull
    private String phone;

    @NotNull
    private String email;

    @NotNull
    private Integer number_gradebook;

    @NotNull
    private Float average_performance;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "student")
    private List<CourseProgress> courseProgresses;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Course course;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
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

    public Integer getNumber_gradebook() {
        return number_gradebook;
    }

    public void setNumber_gradebook(Integer number_gradebook) {
        this.number_gradebook = number_gradebook;
    }

    public Float getAverage_performance() {
        return average_performance;
    }

    public void setAverage_performance(Float average_performance) {
        this.average_performance = average_performance;
    }
}