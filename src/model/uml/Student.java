package model.uml;

import java.util.ArrayList;
import java.util.Collection;

public class Student extends Man {
	private static final ArrayList<Student> STUDENTS = new ArrayList<Student>();

	private final Collection<Science> courses;

	private final String mail;

	private final float schoolRating;

	public Student(String name, String address, String phone, String mail,
			float schoolRating) {
		super(name, address, phone);
		this.mail = mail;
		this.schoolRating = schoolRating;
		courses = new ArrayList<Science>();
		STUDENTS.add(this);
	}

	public static int getCountOfStudents() {
		return STUDENTS.size();
	}

	public static Student getById(int number) {
		return STUDENTS.get(number);
	}

	public float getSchoolRating() {
		return schoolRating;
	}

	public String getMail() {
		return mail;
	}

	/**
	 * Возвращает весь список процессов для всех студентов
	 * 
	 * @return
	 */
	public static final ArrayList<Process> getAllProcesses() {
		ArrayList<Process> result = new ArrayList<Process>();
		for (Student student : STUDENTS) {
			result.addAll(student.getProcesses());
		}
		return result;
	}

	/**
	 * Вместо номера зачётки
	 * 
	 * @return айдишник
	 */
	public int getId() {
		return STUDENTS.indexOf(this);
	}

	public String toString() {
		return getId() + " (" + getName() + ")";
	}

	/**
	 * Возвращает весь список процессов для нашего студента
	 * 
	 * @return
	 */
	private ArrayList<Process> getProcesses() {
		ArrayList result = new ArrayList<Process>();
		for (Science course : courses) {
			result.add(course.getProcessByStudent(this));
		}
		return result;
	}

	/**
	 * Перебираются все курсы и
	 * 
	 * @return средняя успеваемость
	 */
	public float getRating() {
		float result = 0;
		int count = 0;
		for (Process process : getProcesses()) {
			result += process.getFinalMark();
		}
		return result / Process.COUNT_OF_WEEKS;
	}

	/**
	 * Может записаться на курс?
	 * 
	 * @param course
	 *            - курс
	 * @return ещё не записан?
	 */
	public boolean isNotEnvolved(Science course) {
		return !courses.contains(course);
	}

	void involve(Science course) {
		courses.add(course);
	}

	void free(Science course) {
		courses.remove(course);
	}

	/**
	 * Нуружу выдаётся лишь клон списка курсов, чтобы не смогли извне попортить
	 * оригинал
	 * 
	 * @return
	 */
	public Collection<Science> getCourses() {
		ArrayList<Science> result = new ArrayList<Science>();
		for (Science course : courses) {
			result.add(course);
		}
		return result;
	}

	/**
	 * У студентов только фамилия и иннициалы
	 */
	@Override
	public String getName() {
		int position = name.indexOf(' ');
		if (position > -1) {
			return name.substring(0, position + 2) + ".";
		}
		return name;
	}

}
