package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Course {
	
	private String name;
	private int number;
	private double price;
	private String studentIds;
	private String professorsPhone;
	
	public List<Integer> students = new LinkedList<>();
	
	private Professor professor;
	
	public Course(){
		this.number = new Random().nextInt(1000);
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the number
	 */
	public int getNumber() {
		return number;
	}
	/**
	 * @param number the number to set
	 */
	public void setNumber(int number) {
		this.number = number;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param double1 the price to set
	 */
	public void setPrice(Double double1) {
		this.price = double1;
	}

	public void addStudent(Integer student){
		this.getStudents().add(student);
	}
	
	public void removeStudent(Integer student) {
		this.getStudents().remove(this.getStudents().indexOf(student));
	}
	/**
	 * @return the professors
	 */
	public Professor getProfessor() {
		return professor;
	}
	/**
	 * @param professors the professors to set
	 */
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	/**
	 * @return the studentIds
	 */
	public String getStudentIds() {
		return studentIds;
	}
	/**
	 * @param studentIds the studentIds to set
	 */
	public void setStudentIds(String studentIds) {
		this.studentIds = studentIds;
	}
	/**
	 * @return the professorsPhone
	 */
	public String getProfessorsPhone() {
		return professorsPhone;
	}
	/**
	 * @param professorsPhone the professorsPhone to set
	 */
	public void setProfessorsPhone(String professorsPhone) {
		this.professorsPhone = professorsPhone;
	}
	/**
	 * @return the studentsList
	 */
	public List<Integer> getStudents() {
		return students;
	}
	/**
	 * @param studentsList the studentsList to set
	 */
	public void setStudents(List<Integer> studentsList) {
		this.students = studentsList;
	}	
}
