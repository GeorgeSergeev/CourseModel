package web;

import java.util.ArrayList;

import model.uml.Man;
import model.uml.Process;
import model.uml.Professor;
import model.uml.Science;
import model.uml.Student;

import org.json.JSONObject;

public class Serialization {
	/**
	 * Подготавливаем те данные, которые меняются (процессы)
	 * 
	 * @return джейсончик клиенту
	 */
	static JSONObject prepareCurrentState() {
		JSONObject result = new JSONObject();
		ArrayList<JSONObject> jsonOfProcesses = new ArrayList<JSONObject>();
		for (Process process : Student.getAllProcesses()) {
			JSONObject processJSON = new JSONObject();
			processJSON.put("course", process.getCourse().name());
			processJSON.put("student", process.getStudent().getId());
			processJSON.put("marks", process.marks);
			jsonOfProcesses.add(processJSON);
		}
		result.put("processes", jsonOfProcesses);
		return result;
	}

	/**
	 * Подготавливаем те данные, которые не меняются (курсы, студенты,
	 * профессура). Эта функция вызовется только один раз, на стадии
	 * иннициализации модели
	 * 
	 * @return джейсончик клиенту
	 */
	static JSONObject prepareFoundment() {
		JSONObject result = new JSONObject();
		result.put("courses", serializeCourses());
		result.put("students", serializeStudents());
		result.put("professors", serializeProfessors());
		return result;
	}

	private static JSONObject serializeCourses() {
		JSONObject result = new JSONObject();
		for (Science science : Science.values()) {
			JSONObject courseJSON = new JSONObject();
			courseJSON.put("title", science.getTitle());
			courseJSON.put("price", science.getPrice());
			result.put(science.name(), courseJSON);
		}
		return result;
	}

	private static JSONObject serializeMan(Man man) {
		JSONObject jsonOfMan = new JSONObject();
		jsonOfMan.put("name", man.getName());
		jsonOfMan.put("address", man.getAddress());
		jsonOfMan.put("phone", man.getPhone());
		return jsonOfMan;
	}

	private static ArrayList<JSONObject> serializeStudents() {
		ArrayList<JSONObject> jsonOfStudents = new ArrayList<JSONObject>();
		for (int number = 0; number < Student.getCountOfStudents(); number++) {
			Student student = Student.getById(number);
			JSONObject studentJSON = serializeMan(student);
			studentJSON.put("mail", student.getMail());
			studentJSON.put("schoolRating", student.getSchoolRating());
			jsonOfStudents.add(studentJSON);
		}
		return jsonOfStudents;
	}

	private static ArrayList<JSONObject> serializeProfessors() {
		ArrayList<JSONObject> jsonOfProfessors = new ArrayList<JSONObject>();
		for (Science course : Science.values()) {
			Professor professor = course.getProfessor();
			if (professor != null) {
				JSONObject professorJSON = serializeMan(professor);
				professorJSON.put("salary", professor.getSalary());
				professorJSON.put("course", course.name());
				jsonOfProfessors.add(professorJSON);
			}
		}
		return jsonOfProfessors;
	}
}
