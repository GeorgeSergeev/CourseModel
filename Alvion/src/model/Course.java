package model;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Course {

    private String name;
    private int num, studentCount;
    private Float cost;
    private Professor professor;

    public Course(String name, int num, Float cost, Professor professor) {
        this.name = name;
        this.num = num;
        this.cost = cost;
        this.professor = professor;
    }

    @Override
    public boolean equals(Object o){
        if (o == null){
            return false;
        }
        if (o == this){
            return true;
        }
        if (getClass() != o.getClass()){
            return false;
        }
        Course course = (Course) o;
        return (this.getName().equals(course.getName()) &&
                this.getNum() == course.getNum());
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * num + result;
        return result;
    }

    public void addStudent(){
        studentCount++;
    }

    public void deleteStudent(){
        studentCount--;
    }

    public String getName() {
        return name;
    }

    public int getNum() {
        return num;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public Float getCost() {
        return cost;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }
}
