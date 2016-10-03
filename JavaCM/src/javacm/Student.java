/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacm;

/**
 *
 * @author serega
 */
public class Student {
    
    //поля
    private String name;
    private String address;
    private String telephone;
    private String email;
    private int recbookId;
    private float avrProgress;
    
    //
    //методы
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getRecbookId() {
        return recbookId;
    }

    public void setRecbookId(int recbookId) {
        this.recbookId = recbookId;
    }

    public float getAvrProgress() {
        return avrProgress;
    }

    public void setAvrProgress(float avrProgress) {
        this.avrProgress = avrProgress;
    }
    
}
