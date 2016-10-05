/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacm;

import java.util.ArrayList;
/**
 *
 * @author serega
 */
public class EducCourse {
    
    //поля
    private String name;
    private int Id;
    private float price;
    private Professor professor;
    private ArrayList<Student> studentsList = new ArrayList<Student>();
    
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
    
    //курс может вести профессор
    public void addProfessor(Professor professor) {
        
        this.professor = professor;
    }
    
    //добавить студента в список
    public void addStudent(Student newStudent) {
        
        studentsList.add(newStudent);
        
        //Если данный курс ведет профессор, то его оплата будет
        //расти, пропорционально кол-ву обучаемых студентов
        try {
            this.professor.setPayment(studentsList.size(), price);
        } catch (Exception e) {
            
        }
    }
    
    //удалить студента из списка
    public Student removeStudent(int recBookId) {
        
        Student currStudent = null;
        
        for(Student student : studentsList ) {
            
            if(student.getRecbookId() == recBookId) {
                currStudent = student;
                studentsList.remove(student);
                
                //Оплата профессора уменьшится (если он ведет курс)
                try {
                    this.professor.setPayment(studentsList.size(), price);
                } catch (Exception e) {
                    
                }
            }
        }
        
        return currStudent;
    }
    
    
}
