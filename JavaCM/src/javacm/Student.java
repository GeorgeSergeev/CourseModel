/*
Сущность "Студент" имеет свойства: имя, адресс, телефон, емайл, 
номер зачетной книжки, оценка средней успеваемости, и помнит курсы
на которых обучается.
Студент может:
вычислить свою общую среднюю успеваемость calculateAvrProgress;
записаться на другой дополнительный курс addCourse;
посмотреть средний балл и финальную оценк по заданному курсу;
получить список пройденых курсов

Студент приписан к некоторому курсу
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
    //список для хранения пройденых курсов с оценками
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

    public void setAvrProgress(float avrProgress) {
        this.avrProgress = avrProgress;
    }

    
    //вычисление средней успеваемости студента
    //как (сумма финальных оценок по каждому курсу)/кол-во пройденых курсов
    public void calculateAvrProgress() {
        
        float sum = 0;
        
        for(int i = 0; i < coursesList.size(); i++) {
            sum += coursesList.get(i).getFinalScore();
        }
        setAvrProgress(sum/coursesList.size());
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
