package model;

import javafxComponents.Controllers.DialogManager;


import java.util.ArrayList;

public class CoursePassing {

    private boolean isFinished;
    private Course course;
    private Student student;
    private int finalMark;
    private ArrayList<Integer> rating;

    public CoursePassing(Course course, Student student) {
        this.course = course;
        this.student = student;
        isFinished = false;
        rating = new ArrayList<Integer>();
        finalMark = 0;
        course.addStudent();
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Course getCourse() {
        return course;
    }

    public Student getStudent() {
        return student;
    }

    public ArrayList<Integer> getRating() {
        return rating;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getFinalMark() {
        return finalMark;
    }

    public void getFinalRating(){

        if (rating.size() == 0){
            return;
        }

        isFinished = true;


        for (int mark:
             rating) {
            finalMark+=mark;
        }

        finalMark/=rating.size();

        try {
            String message = "Студент " + student.getName() + " завершил курс "
                    + course.getName() + " c оценкой " + finalMark+"!";
            DialogManager.showInfoDialog("Окончание курса", message);
        } catch (Exception e) {
            e.printStackTrace();
        }

        student.updateAverageRating();
    }

    public void getAverageRating(){
        int sum= 0 ,count = 0;

        for (int mark:
             rating) {
            sum += mark;
            count++;
        }

        float averageRating = (float) sum /count;

        try {
            String message = "Студент " + student.getName() + " на куосе "
                    + course.getName() + " имеет средний балл " + averageRating+"!";
            DialogManager.showInfoDialog("Средний балл", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}