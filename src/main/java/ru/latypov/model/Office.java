package ru.latypov.model;

import javax.persistence.*;

/*Модель заполнения таблицы Офис*/
@Entity
@Table(name = "OFFICE")
public class Office {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // 'Уникальный идентификатор
    @Column(name = "ORGANIZATION_ID")
    private Integer organization_id; //'Поле дял связи с таблицей Организация'
    @Column(name = "NAME")
    private String name; //'Имя'
    @Column(name = "ADDRESS")
    private String address;    // 'Адрес'
    @Column(name = "PHONE")
    private String phone; //'Телефон'
    @Column(name = "IS_ACTIVE")
    private Boolean is_active; //'Статус'

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
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

    public Boolean getIs_active() {
        return is_active;
    }

    public void setIs_active(Boolean is_active) {
        this.is_active = is_active;
    }
}
