package model.uml;

import java.util.ArrayList;
import java.util.Collection;


/**
 * я хотел сделать все классы констант энумераци€ми
 * (чтобы не возитьс€ со статическим хранением экземпл€ров, но
 * ограничилс€ только классом Science, про остальные классы
 * написано в комменте к классу Model)
 * @author Arsen Pan
 *
 */
public enum Science {
	COURSE1("Numerical methods", 100), COURSE2("Probability theory", 200), COURSE3(
			"Computing science", 200), COURSE4("Lambda-calculus", 400);
	private final String title;
	private final float price;
	private final Collection<Process> processes;
	private Professor professor;

	Science(String title, float price) {
		this.title = title;
		this.price = price;
		processes = new ArrayList<Process>();
		professor = null;
	}

	public String toString() {
		return name();
	}

	public Professor getProfessor() {
		return professor;
	}

	public void addStudent(Student student) {
		Process studentProcess = getProcessByStudent(student);
		if (studentProcess == null) {
			processes.add(new Process(this, student));
			student.involve(this);
		}
	}

	public void deleteStudent(Student student) {
		processes.remove(getProcessByStudent(student));
		student.free(this);
	}

	public Process getProcessByStudent(Student student) {
		for (Process p : processes) {
			if (p.getStudent() == student) {
				return p;
			}
		}
		return null;
	}

	void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getTitle() {
		return title;
	}

	public float getPrice() {
		return price;
	}

}
