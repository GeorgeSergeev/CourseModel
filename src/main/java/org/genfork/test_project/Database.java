package org.genfork.test_project;

import org.genfork.test_project.pojo.Course;
import org.genfork.test_project.pojo.Professor;
import org.genfork.test_project.pojo.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * User: <a href="http://gencloud.solutions">GenCloud</a>
 * Date: 2017/07
 */
public class Database {
	private List<Student> students = new ArrayList<>();
	private List<Course> courses = new ArrayList<>();
	private List<Professor> professors = new ArrayList<>();

	public void initialize() {
		students.add(new Student("Звягинцев Максим Петрович", "some addr", "some telephone", "some mail", 1)
				.setBalance(2000));
		students.add(new Student("Исак Александр Виторович", "some addr", "some telephone", "some mail", 2)
				.setBalance(2000));
		students.add(new Student("Усков Василий Дмитриевич", "some addr", "some telephone", "some mail", 3)
				.setBalance(2000));
		students.add(new Student("Алешенков Владимир Геннадиевич", "some addr", "some telephone", "some mail", 4)
				.setBalance(2000));

		final Professor professor1 = new Professor("Никулин Денис Викторович", "some addr", "some telephone", 500);
		final Professor professor2 = new Professor("Заброда Валерий Денисович", "some addr", "some telephone", 700);
		final Professor professor3 = new Professor("Голубенко Никита Сергеевич", "some addr", "some telephone", 900);

		professors.add(professor1);
		professors.add(professor2);
		professors.add(professor3);

		courses.add(new Course("Физика", 1, 1000).setProfessor(professor1));
		courses.add(new Course("Кибернетика", 2, 1500).setProfessor(professor2));
		courses.add(new Course("Пение", 3, 500));
		courses.add(new Course("Низкоуровневые языки программирования", 4, 2000).setProfessor(professor3));
	}

	public List<Student> getStudents() {
		return students;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public List<Professor> getProfessors() {
		return professors;
	}
}
