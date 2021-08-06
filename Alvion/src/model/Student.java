package model;
import Collections.CollectionCourseList;
import Collections.CollectionCoursePassingList;
import javafxComponents.Controllers.DialogManager;

import java.util.ArrayList;

public class Student {

    private float averageRating;
    private String name, adress, phone,email;
    private Integer num;

    public Student(String name, String adress, String phone, String email, Integer num) {
        this.name = name;
        this.adress = adress;
        this.phone = phone;
        this.email = email;
        this.num = num;
    }

    @Override
    public boolean equals (Object o){
        if (o == null){
            return false;
        }
        if (o == this){
            return true;
        }
        if (getClass() != o.getClass()){
            return false;
        }
        Student student = (Student) o;
        return (this.getName().equals(student.getName()) &&
                this.getNum() == student.getNum());
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + num;
        return result;
    }

    public void canEnroll(){

        ArrayList<Course> courses = new ArrayList<Course>();

        courses.addAll(CollectionCourseList.getCourseList());

        for (CoursePassing coursePassing:
                CollectionCoursePassingList.getCoursePassingListList()) {
            if (coursePassing.getStudent().equals(this)){
                courses.remove(coursePassing.getCourse());
            }
        }

        if (courses.size()==0){
            DialogManager.showInfoDialog("Доступные курсы",
                    "В данный момент студент " + this.name + " не имеет доступных курсов!");
        }
        else {
            String message = "Студент " + this.name + " может быть хаписан на:\n" ;

            for (Course course:
                 courses) {
                message+=course.getName() + "\n";
            }

            DialogManager.showInfoDialog("Доступные курсы", message);
        }
    };

    public void completedCourses(){

        ArrayList<Course> courses = new ArrayList<Course>();

        for (CoursePassing coursePassing:
                CollectionCoursePassingList.getCoursePassingListList()) {
            if (coursePassing.getStudent().equals(this)&&
                    coursePassing.isFinished()){
                courses.add(coursePassing.getCourse());
            }
        }

        if (courses.size()==0){
            DialogManager.showInfoDialog("Доступные курсы",
                    "Студент " + this.name + " не завершил ни одного курса!");
        }
        else {
            String message = "Студент " + this.name + " завершил следующие курсы:\n" ;

            for (Course course:
                    courses) {
                message+=course.getName() + "\n";
            }

            DialogManager.showInfoDialog("Доступные курсы", message);
        }
    };

    public float getAverageRating() {
        return averageRating;
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

    public String getEmail() {
        return email;
    }

    public Integer getNum() {
        return num;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public void updateAverageRating(){
        int sumOfFinalMarks=0, count=0;

        for (CoursePassing coursePassing:
                CollectionCoursePassingList.getCoursePassingListList()) {
            if (coursePassing.getStudent().equals(this) && coursePassing.isFinished()){
                sumOfFinalMarks+=coursePassing.getFinalMark();
                count++;
            }
        }

        averageRating=(float) sumOfFinalMarks/count;
    }



}