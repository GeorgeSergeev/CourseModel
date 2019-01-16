package ru.latypov.model;

import javax.persistence.*;

/*Модель заполнения таблицы Курс*/
@Entity
@Table(name = "Kurs")
public class Kurs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // 'Уникальный идентификатор
    @Column(name = "NAME")
    private String name; //'Название'
    @Column(name = "PROFESSOR_ID")
    private Integer professor_id; //'свзяь с профессорами'
    @Column(name = "NUMBER")
    private String number;    // 'номер'
    @Column(name = "SALE")
    private Float sale; //'Оплата'

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getProfessor_id() {
        return professor_id;
    }

    public void setProfessor_id(Integer professor_id) {
        this.professor_id = professor_id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Float getSale() {
        return sale;
    }

    public void setSale(Float sale) {
        this.sale = sale;
    }
}
