package ru.latypov.model;

import javax.persistence.*;

/*Модель заполнения таблицы Организация*/
@Entity
@Table(name = "ORGANIZATION")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // 'Уникальный идентификатор
    @Column(name = "NAME")
    private String name; //'Название'
    @Column(name = "FULL_NAME")
    private String full_name; //'Полное название'
    @Column(name = "INN")
    private String inn;    // 'ИНН'
    @Column(name = "KPP")
    private String kpp; //'КПП'
    @Column(name = "ADDRESS")
    private String address; //'Адрес'
    @Column(name = "PHONE")
    private String phone; //'Телефон'
    @Column(name = "IS_ACTIVE")
    private Boolean is_active;  // 'Статус'

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

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getKpp() {
        return kpp;
    }

    public void setKpp(String kpp) {
        this.kpp = kpp;
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

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }
}
