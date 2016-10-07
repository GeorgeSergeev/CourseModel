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
public class Student {
    
    //поля
    private String studentName;
    private String address;
    private String telephone;
    private String email;
    private int recbookId;
    private float avrProgress;
    private ArrayList<CourseStatus> coursesList = new ArrayList<CourseStatus>();
    
    //конструкторы
    public Student() {
        
    }

    public Student(String studentName, String address, String telephone, String email, int recbookId) {
        this.studentName = studentName;
        this.address = address;
        this.telephone = telephone;
        this.email = email;
        this.recbookId = recbookId;
    }
    
    
    //методы
    public String getName() {
        return studentName;
    }

    public void setName(String studentName) {
        this.studentName = studentName;
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

    public void setAvrProgress() {
        
        float sum = 0;
        
        for(int i = 0; i < coursesList.size(); i++) {
            sum += coursesList.get(i).getFinalScore();
        }
        this.avrProgress = sum/coursesList.size();
    }
    
    //студент будет проходить еще один курс
    public void addCourse(String nameCourse) {
        
        coursesList.add(new CourseStatus(nameCourse));
       
    }
    
    //посмотреть(получить) статус курса по названию курса
    public CourseStatus getCourse(String nameCourse) {
        
        for(CourseStatus currCourse : coursesList) {
            if(currCourse.getListenCurse().equals(nameCourse)) {
                return currCourse;
            }
        }
        return null;
    }
    
    //Получить список курсов
    public void getCourseListNames() {
        
        for(CourseStatus currCourse : coursesList) {
            System.out.println(currCourse.getListenCurse());
        }
    }
    
}
