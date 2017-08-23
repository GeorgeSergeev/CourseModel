package org.genfork.test_project.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * User: <a href="http://gencloud.solutions">GenCloud</a>
 * Date: 2017/07
 */
public class Course implements Serializable {
	private String name;
	private int number;
	private float price;

	private Professor professor;

	private CourseProgress progress = new CourseProgress(false);

	private Set<Student> students = new HashSet<>();

	public Course() {
	}

	public Course(String name, int number, float price) {
		this.name = name;
		this.number = number;
		this.price = price;
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

	public CourseProgress getProgress() {
		return progress;
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

	public void setRating(List<Integer> rating) {
		for (Integer i : rating) {
			progress.addRating(i);
		}
	}

	public Course setProfessor(Professor professor) {
		this.professor = professor;
		return this;
	}

	public boolean addStudent(Student student) {
		return students.add(student);
	}

	public void removeStudent(Student student) {
		students
				.stream()
				.filter(data -> Objects.equals(student.getName(), data.getName()))
				.findFirst()
				.ifPresent(data -> students.remove(data));
	}

	@Override
	public String toString() {
		return "Курс-" + name + ", Номер-" + number + ", Стоимость-" + price;
	}
}
