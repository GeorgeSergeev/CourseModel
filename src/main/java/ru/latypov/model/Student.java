package ru.latypov.model;


import javax.persistence.*;

/*Модель заполнения таблицы Пользователь*/
@Entity
@Table(name = "USER")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // 'Уникальный идентификатор
    @Column(name = "NAME")
    private String name; //'ИМЯ'
    @Column(name = "ADDRESS")
    private String address; //'адрес'
    @Column(name = "PHONE")
    private String phone; // 'Телефон'

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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getNumber_doc() {
        return number_doc;
    }

    public void setNumber_doc(String number_doc) {
        this.number_doc = number_doc;
    }

    public String getMiddle_progress() {
        return middle_progress;
    }

    public void setMiddle_progress(String middle_progress) {
        this.middle_progress = middle_progress;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }

    public String getKurs_end() {
        return kurs_end;
    }

    public void setKurs_end(String kurs_end) {
        this.kurs_end = kurs_end;
    }

    @Column(name = "MAIL")
    private String mail; // 'почта'
    @Column(name = "NUMBER_DOC")
    private String number_doc;    // 'номер зачетки'
    @Column(name = "MIDDLE_PROGRESS ")
    private String middle_progress ; //'средний прогресс'
    @Column(name = "IS_ACTIVE")
    private Boolean is_active;
    @Column(name = " KURS_END")
    private String  kurs_end; //'прослушные курсы'

}