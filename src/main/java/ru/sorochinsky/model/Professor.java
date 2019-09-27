package ru.sorochinsky.model;

import javax.persistence.*;

/**
 * Simple JavaBean object that represents role of {@link Professor}
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

@Entity // This tells Hibernate to make a table out of this class
public class Professor {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String name;

    private String address;

    private String phone;

    private Float payment;

//    Некоторые курсы не требуют инструктора и расчитаны на самостоятельное изучение
//    optional = true - необязательное поле
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
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

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
}
