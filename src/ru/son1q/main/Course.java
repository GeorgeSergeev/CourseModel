package ru.son1q.main;

import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.management.RuntimeErrorException;

public class Course implements Serializable {
	private String name;
	private int number;
	private float price;
	private Professor professor;
	private List<Student> listStudents;
	private static int id;
	
	public Course(String name, float price, Professor professor) {
		if (ValidFormat.isCourseValid(name, price) && professor != null)
			throw new RuntimeException("Некорректный ввод для объекта 'курс'.");
		this.name = name;
		this.number = ++id;
		this.price = price;
		this.professor = professor;
		listStudents = new LinkedList<Student>();
	}
	
	public Course(String name, float price) {
		if (ValidFormat.isCourseValid(name, price))
			throw new RuntimeException("Некорректный ввод для объекта 'курс'.");
		this.name = name;
		this.number = ++id;
		this.price = price;
		this.professor = null;
		listStudents = new LinkedList<Student>();
	}
	
	public Course() { }
	
	public void addStudent(Student student) {
		listStudents.add(student);
		student.getListCourses().add(this);
		student.getListPassingCounrs().add(new PassingCours(this));
	}
	
	public void deleteStudent(Student student) {
		if (ValidFormat.checkContaints(student, student.getListCourses())) {
			listStudents.remove(student);
			student.getListCourses().remove(student);
			student.getListPassingCounrs().remove(student.getPassingCourse(this));
		}
	}
	
	public String getName() {
		return name;
	}
	
	public int getNumber() {
		return number;
	}
	
	public float getPrice() {
		return price;
	}
	
	public Professor getProfessor() {
		return professor;
	}
	
	public List<Student> getListStudents() {
		return listStudents;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setPrice(float price) {
		this.price = price;
	}
	
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	
	@Override public String toString() {
		return professor == null ? "Курс: " + name + "." : "Курс: " + name + ". Профессор: " + professor.getName();  
	}
	
	@Override public boolean equals(Object o) {
		if (!(o instanceof Course))
			return false;
		Course course = (Course) o;
		return course.name.equals(name) && course.number == number;
	}
	
}
