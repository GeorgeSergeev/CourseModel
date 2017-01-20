package ru.son1q.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student implements Serializable {
	private String name;
	private String address;
	private String phoneNumber;
	private String email;
	private int numberRecordBook; // Предпочтения примитивным типам чем к классам обверткам
	private float averageProgress;
	private static int id;
	private List<Course> listCourses;
	private List<PassingCours> listPassing;
	
	public Student(String name, String address, String phoneNumber, String email) {
		if (ValidFormat.isStudentValid(name, address, phoneNumber, email))
			throw new RuntimeException("Некорректный ввод для объекта 'студент'.");
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.numberRecordBook = ++id;
		averageProgress = 0f;
		listCourses = new ArrayList<Course>();
		listPassing = new ArrayList<PassingCours>();
	}
	
	public Student() { }
	
	public void regestrationCourses(Course course) {
		if (course != null)
			course.addStudent(this);
	}
	
	public List<Course> getCompleteCourses() {
		List<Course> result = new ArrayList<Course>();
		for(PassingCours passing : listPassing)
			if (passing.getFinalEvaluation() != 0)
				result.add(passing.getCurrentCourse());
		return result;
	}
	
	public void getAverageEvaluation() {
        float sum = 0;
        int evaluation = 0;
        for (PassingCours passing : listPassing) {
            if ((evaluation = passing.getFinalEvaluation()) != 0)
                sum += evaluation;
        }
        averageProgress = listCourses.size() > 0 ? sum / listCourses.size() : 0;
    }
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public String getEmail() {
		return email;
	}
	
	public int getNumberRecordBook() {
		return numberRecordBook;
	}
	
	public float getAverageProgress() {
		return averageProgress;
	}
	
	public List<Course> getListCourses() {
		return listCourses;
	}
	
	public List<PassingCours> getListPassingCounrs() {
		return listPassing;
	}
	
	 public PassingCours getPassingCourse(Course course) {
	        if (course != null) {
	            for (PassingCours passing : listPassing) {
	                if (course.equals(passing.getCurrentCourse())) {
	                    return passing;
	                }
	            }
	        }
	    	return null;
	    }
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAdress(String address) {
		this.address = address;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setNumberRecordBook(int numberRecordBook) {
		this.numberRecordBook = numberRecordBook;
	}
	
	public void setAverageProgress(float averageProgress) {
		this.averageProgress = averageProgress;
	}
	
	@Override public String toString() {
		return "\nИмя: " + name + ".\nНомер зачетки: " + numberRecordBook;
	}
	
	@Override public boolean equals(Object o) {
		if (!(o instanceof Student))
			return false;
		Student stud = (Student) o;
		return stud.name.equals(name) && stud.numberRecordBook == numberRecordBook;
	}
}
