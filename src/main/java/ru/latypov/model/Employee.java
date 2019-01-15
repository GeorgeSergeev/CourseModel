package ru.latypov.model;


import javax.persistence.*;

/*Модель заполнения таблицы Пользователь*/
@Entity
@Table(name = "USER")

public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // 'Уникальный идентификатор
    @Column(name = "DOCUMENT_ID")
    private Integer doc_type_id; //'Поле дял связи с таблицей Тип Документа'
    @Column(name = "СOUNTRY_ID")
    private Integer сountry_id; //'Поле для связи с таблицей Гражданство'
    @Column(name = "OFFICE_ID")
    private Integer office_id;    // 'Поле для связи с таблицей Офис'
    @Column(name = "FIRST_NAME")
    private String first_name; //'Имя'
    @Column(name = "SECOND_NAME")
    private String second_name; //'Фамилия'
    @Column(name = "MIDDLE_NAME")
    private String middle_name; //'Отчество'
    @Column(name = "POSITION")
    private String position;  // 'Позиция'
    @Column(name = "PHONE")
    private String phone; // 'Телефон'
    @Column(name = "IS_IDENTIFIED")
    private Boolean is_identified; // 'Идентификация'
    @Column(name = "IS_ACTIVE")
    private Boolean is_active;  // 'Статус'

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDoc_type_id() {
        return doc_type_id;
    }

    public void setDoc_type_id(Integer doc_type_id) {
        this.doc_type_id = doc_type_id;
    }

    public Integer getСountry_id() {
        return сountry_id;
    }

    public void setСountry_id(Integer сountry_id) {
        this.сountry_id = сountry_id;
    }

    public Integer getOffice_id() {
        return office_id;
    }

    public void setOffice_id(Integer office_id) {
        this.office_id = office_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getMiddle_name() {
        return middle_name;
    }

    public void setMiddle_name(String middle_name) {
        this.middle_name = middle_name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getIs_identified() {
        return is_identified;
    }

    public void setIs_identified(Boolean is_identified) {
        this.is_identified = is_identified;
    }

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }
}