package org.genfork.test_project.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * User: <a href="http://gencloud.solutions">GenCloud</a>
 * Date: 2017/07
 */
public class Student implements Serializable {
	private String name;
	private String address;
	private String telephone;
	private String email;
	private int record_book;

	private float balance;

	private Set<Course> courses = new HashSet<>();

	public Student(String name, String address, String telephone, String email, int record_book) {
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.email = email;
		this.record_book = record_book;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getTelephone() {
		return telephone;
	}

	public String getEmail() {
		return email;
	}

	public int getRecord_book() {
		return record_book;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public boolean addCourse(Course course) {
		return balance >= course.getPrice() && courses.add(course);
	}

	public float getBalance() {
		return balance;
	}

	public Student setBalance(float balance) {
		this.balance = balance;
		return this;
	}

	@Override
	public String toString() {
		return "Ситудент: " + name + " Адресс: " + address + " Телефон: " + telephone + " Email: "
				+ email + " Номер зачетки: " + record_book + " Баланс: " + balance + " Курсы: " + courses;
	}
}
