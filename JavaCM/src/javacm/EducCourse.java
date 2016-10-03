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
public class EducCourse {
    
    //поля
    private String name;
    private int Id;
    private float price;
    
    //конструкторы
    public EducCourse() {    
        
    }

    public EducCourse(String name, int Id, float price) {
        this.name = name;
        this.Id = Id;
        this.price = price;
    }
    
    //методы
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    
    
}
