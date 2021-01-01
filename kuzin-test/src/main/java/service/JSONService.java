package service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import controller.MainController;
import model.Course;
import model.CourseFlow;
import model.Professor;
import model.Student;

public class JSONService {

	public static final String JSON_FOLDER = "//json//";
	public static final String STUDENTS_JSON_LIST_FILE = "Students.json";
	public static final String COURSES_JSON_LIST_FILE = "Courses.json";
	public static final String PROFESSORS_JSON_LIST_FILE = "Professors.json";
	public static final String COURSE_FLOW_JSON_LIST_FILE = "CourseFlow.json";

	/*
	 * --- STUDENTS PART ---
	 */
	public static Map<Integer, Student> readStudents(ServletContext context) {
		Map<Integer, Student> studentsList = new HashMap<>();

		try {
			File studentsJSONFile = new File(context.getClassLoader().getResource(STUDENTS_JSON_LIST_FILE).getFile());

			InputStream is = new FileInputStream(studentsJSONFile);

			JSONParser jsonParser = new JSONParser();
			JSONArray jsonArray = (JSONArray) jsonParser.parse(new InputStreamReader(is, "UTF-8"));

			JSONObject tmpObject;
			Student tmpStudent;

			for (Object s : jsonArray) {
				tmpObject = (JSONObject) s;
				tmpStudent = new Student();

				tmpStudent.setName((String) tmpObject.get("name"));
				tmpStudent.setEmail((String) tmpObject.get("email"));
				tmpStudent.setAddress((String) tmpObject.get("adress"));
				tmpStudent.setTelephone((String) tmpObject.get("phone"));
				tmpStudent.setScoreBookNum((Integer.parseInt(tmpObject.get("scoreBookNum").toString())));

				String courseFlowIdsString = tmpObject.get("courseFlowIds").toString();
				if (courseFlowIdsString.contains(",")) {
					for (String courseFlowIdString : courseFlowIdsString.split(",")) {
						CourseFlow cf = new CourseFlow();
						cf.setCourseFlowId(courseFlowIdString);
						tmpStudent.addCourseFlow(cf);
					}
				} else {
					if (!courseFlowIdsString.isEmpty()) {
						CourseFlow cf = new CourseFlow();
						cf.setCourseFlowId(courseFlowIdsString);
						tmpStudent.addCourseFlow(cf);
					}
				}

				studentsList.put(tmpStudent.getScoreBookNum(), tmpStudent);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return studentsList;
	}

	public static boolean writeStudents(Collection<Student> students, ServletContext context) {

		JSONArray studentsJsonArray = new JSONArray();

		JSONObject studentJsonItem;
		StringBuffer tmpCourseFlowIdsStringBuffer;

		for (Student s : students) {
			studentJsonItem = new JSONObject();

			studentJsonItem.put("name", s.getName());
			studentJsonItem.put("phone", s.getTelephone());
			studentJsonItem.put("adress", s.getAddress());
			studentJsonItem.put("email", s.getEmail());
			studentJsonItem.put("scoreBookNum", s.getScoreBookNum());

			tmpCourseFlowIdsStringBuffer = new StringBuffer("");

			for (CourseFlow cf : s.getCourseFlow()) {
				if (s.getCourseFlow().indexOf(cf) == 0) {
					tmpCourseFlowIdsStringBuffer.append(cf.getCourseFlowId());
				} else {
					tmpCourseFlowIdsStringBuffer.append(",").append(cf.getCourseFlowId());
				}
			}
			studentJsonItem.put("courseFlowIds", tmpCourseFlowIdsStringBuffer.toString());

			studentsJsonArray.add(studentJsonItem);
		}

		try {

			// Writing to a file
			File file = new File(context.getClassLoader().getResource(STUDENTS_JSON_LIST_FILE).getFile());
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			System.out.println("Writing JSON object to file");
			System.out.println("-----------------------");
			System.out.print(studentsJsonArray.toJSONString());

			fileWriter.write(studentsJsonArray.toJSONString());
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	/*
	 * --- COURSES PART ---
	 */

	public static Map<Integer, Course> readCourses(ServletContext context) {
		Map<Integer, Course> coursesList = new HashMap();

		try {
			File coursesJSONFile = new File(context.getClassLoader().getResource(COURSES_JSON_LIST_FILE).getFile());

			InputStream is = new FileInputStream(coursesJSONFile);

			JSONParser jsonParser = new JSONParser();
			JSONArray jsonArray = (JSONArray) jsonParser.parse(new InputStreamReader(is, "UTF-8"));

			JSONObject tmpObject;
			Course tmpCourse;

			for (Object o : jsonArray) {
				tmpObject = (JSONObject) o;
				tmpCourse = new Course();

				tmpCourse.setName((String) tmpObject.get("name"));
				tmpCourse.setNumber(Integer.parseInt(tmpObject.get("number").toString()));
				tmpCourse.setPrice((Double) tmpObject.get("price"));

				List<Integer> studentsScoreBooksNums = new ArrayList<>();

				for (String s : tmpObject.get("studentIds").toString().split(",")) {
					if (!s.isEmpty()) {
						studentsScoreBooksNums.add(Integer.parseInt(s));
					}
				}

				tmpCourse.setStudents(studentsScoreBooksNums);

				tmpCourse.setProfessorsPhone((String) tmpObject.get("professorPhone"));

				/*
				 * if (professorsPhone != null && !professorsPhone.isEmpty()) {
				 * for (Professor professor : professors) { if
				 * (professor.equals(professorsPhone)) {
				 * tmpCourse.setProfessor(professor); break; } } }
				 */

				coursesList.put(tmpCourse.getNumber(), tmpCourse);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return coursesList;
	}

	public static boolean writeCourses(Collection<Course> courses, ServletContext context) {

		JSONArray courseJsonArray = new JSONArray();

		JSONObject courseJsonItem;

		for (Course c : courses) {
			courseJsonItem = new JSONObject();

			courseJsonItem.put("name", c.getName());
			courseJsonItem.put("number", c.getNumber());
			courseJsonItem.put("price", c.getPrice());

			StringBuffer sbStudents = new StringBuffer();

			for (Integer student : c.getStudents()) {
				if (c.getStudents().indexOf(student) == 0) {
					sbStudents.append(String.valueOf(student));
				} else {
					sbStudents.append(",").append(String.valueOf(student));
				}
			}

			courseJsonItem.put("studentIds", sbStudents.toString());

			courseJsonItem.put("professorPhone", c.getProfessorsPhone());

			courseJsonArray.add(courseJsonItem);
		}

		try {

			// Writing to a file
			File file = new File(context.getClassLoader().getResource(COURSES_JSON_LIST_FILE).getFile());
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			System.out.println("Writing JSON object to file");
			System.out.println("-----------------------");
			System.out.print(courseJsonArray.toJSONString());

			fileWriter.write(courseJsonArray.toJSONString());
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	/*
	 * --- PROFESSORS PART ---
	 */

	public static Map<String, Professor> readProfessors(ServletContext context) {
		Map<String, Professor> professorsList = new HashMap<>();

		try {
			File professorsJSONFile = new File(
					context.getClassLoader().getResource(PROFESSORS_JSON_LIST_FILE).getFile());

			InputStream is = new FileInputStream(professorsJSONFile);

			JSONParser jsonParser = new JSONParser();
			JSONArray jsonArray = (JSONArray) jsonParser.parse(new InputStreamReader(is, "UTF-8"));

			JSONObject tmpObject;
			Professor tmpProfessor;

			for (Object o : jsonArray) {
				tmpObject = (JSONObject) o;
				tmpProfessor = new Professor();

				tmpProfessor.setName((String) tmpObject.get("name"));
				tmpProfessor.setAddress(tmpObject.get("adress").toString());
				tmpProfessor.setTelephone(tmpObject.get("telephone").toString());
				tmpProfessor.setSalary((Double) tmpObject.get("payment"));

				professorsList.put(tmpProfessor.getTelephone(), tmpProfessor);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return professorsList;
	}

	public static boolean writeProfessors(Collection<Professor> professor, ServletContext context) {

		JSONArray professorJsonArray = new JSONArray();

		JSONObject professorJsonItem;

		for (Professor p : professor) {
			professorJsonItem = new JSONObject();

			professorJsonItem.put("name", p.getName());
			professorJsonItem.put("adress", p.getAddress());
			professorJsonItem.put("telephone", p.getTelephone());
			professorJsonItem.put("payment", p.getSalary());

			professorJsonArray.add(professorJsonItem);
		}

		try {

			// Writing to a file
			File file = new File(context.getClassLoader().getResource(PROFESSORS_JSON_LIST_FILE).getFile());
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			System.out.println("Writing JSON object to file");
			System.out.println("-----------------------");
			System.out.print(professorJsonArray.toJSONString());

			fileWriter.write(professorJsonArray.toJSONString());
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}

	/*
	 * --- COURSE FLOW PART ---
	 */

	public static Map<String, CourseFlow> readCourseFlow(ServletContext context) {
		Map<String, CourseFlow> courseFlowList = new HashMap<>();

		try {
			File courseFlowJSONFile = new File(
					context.getClassLoader().getResource(COURSE_FLOW_JSON_LIST_FILE).getFile());

			InputStream is = new FileInputStream(courseFlowJSONFile);

			JSONParser jsonParser = new JSONParser();
			JSONArray jsonArray = (JSONArray) jsonParser.parse(new InputStreamReader(is, "UTF-8"));

			JSONObject tmpObject;
			CourseFlow tmpCourseFlow;
			List<Integer> scores;

			for (Object o : jsonArray) {
				tmpObject = (JSONObject) o;
				tmpCourseFlow = new CourseFlow();
				scores = new ArrayList<>();

				tmpCourseFlow.setCourseFlowId((String) tmpObject.get("id"));

				for (String score : ((String) tmpObject.get("scores")).split(",")) {
					if (!score.isEmpty()) {
						scores.add(Integer.parseInt(score));
					}
				}

				tmpCourseFlow.setScores(scores);

				Course tmpCourse = new Course();
				tmpCourse.setNumber(Integer.parseInt(tmpObject.get("courseNum").toString()));
				tmpCourseFlow.setCourse(tmpCourse);

				courseFlowList.put(tmpCourseFlow.getCourseFlowId(), tmpCourseFlow);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) { // TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return courseFlowList;
	}

	public static boolean writeCourseFlow(Collection<CourseFlow> courseFlow, ServletContext context) {

		JSONArray courseFlowJsonArray = new JSONArray();

		JSONObject courseFlowJsonItem;

		for (CourseFlow cf : courseFlow) {
			courseFlowJsonItem = new JSONObject();

			courseFlowJsonItem.put("id", cf.getCourseFlowId());
			StringBuffer sbScores = new StringBuffer();

			for (Integer score : cf.getScores()) {
				if (cf.getScores().indexOf(score) == 0) {
					sbScores.append(score);
				} else {
					sbScores.append(",").append(score);
				}
			}

			courseFlowJsonItem.put("scores", sbScores.toString());

			courseFlowJsonItem.put("courseNum", String.valueOf(cf.getCourse().getNumber()));

			courseFlowJsonArray.add(courseFlowJsonItem);
		}

		try {

			// Writing to a file
			File file = new File(context.getClassLoader().getResource(COURSE_FLOW_JSON_LIST_FILE).getFile());
			file.createNewFile();
			FileWriter fileWriter = new FileWriter(file);
			System.out.println("Writing JSON object to file");
			System.out.println("-----------------------");
			System.out.print(courseFlowJsonArray.toJSONString());

			fileWriter.write(courseFlowJsonArray.toJSONString());
			fileWriter.flush();
			fileWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}
}
