package model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class Professor {

    private String name, adress,phone;
    private float payment;

    public Professor(String name, String adress, String phone, Float payment) {
        this.name = name;
        this.adress = adress;
        this.phone = phone;
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public String getPhone() {
        return phone;
    }

    public float getPayment() {
        return payment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPayment(float payment) {
        this.payment = payment;
    }
}
