package ru.son1q.main;

import java.io.Serializable;

public class Professor implements Serializable {
	private String name;
	private String address;
	private String phoneNumber;
	private float salary;
	
	public Professor(String name, String address, String phoneNumber, float salary) {
		if (ValidFormat.isProfessorValid(name, address, phoneNumber, salary))
			throw new RuntimeException("Некорректный ввод для объекта 'профессор'.");
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.salary = salary;
	}
	
	public Professor() { }
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public float getSalary() {
		return salary;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setSalary(float salary) {
		this.salary = salary;
	}
	
	@Override public String toString() {
		return "Профессор: " + name + ".";
	}
	
	@Override public boolean equals(Object o) {
		if (!(o instanceof Professor))
			return false;
		Professor prof = (Professor) o;
		return prof.name.equals(name) && prof.phoneNumber.equals(phoneNumber);
	}
}
