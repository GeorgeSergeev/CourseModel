package sev.alvioneurope.coursemodel.impl;

import java.io.IOException;
import java.util.List;

import sev.alvioneurope.coursemodel.impl.DAOservice.BuilderCourse;
import sev.alvioneurope.coursemodel.impl.DAOservice.BuilderProfessor;
import sev.alvioneurope.coursemodel.impl.DAOservice.BuilderStudent;

//Обьект: публичный интерфейс DAO. Интерфейс для клиентов
public interface DAO {
	//------------------- Create -------------------
	BuilderStudent		createStudent(Student student);
	BuilderCourse		createCourse(Course course);
	BuilderProfessor	createProfessor(Professor professor);
	
	//------------------- Read ----------------------
	Student			readStudent(int gradeBookNum);
	Course			readCourse(int numCourse);	
	Professor		readProfessor(String name, String address);
	
	//------------------- ReadAll ------------------
	List<Student>	 	readStudentAll();
	List<Course>		readCourseAll();
	List<Professor>		readProfessorAll();
	
	//------------------- Update --------------------
	void	updateStudent(Student student);
	void	updateCourse(Course course);
	void	updateProfessor(Professor professor);

	//------------------- Delete --------------------
	void deleteStudent(Student student);
	void deleteCourse(Course course);
	void deleteProfessor(Professor professor);
	
	//------------------- Индивидуально для StudyImpl --------------------
	StudyImpl readStudy();
	void updateStudy() throws IOException;

	static DAO getDAO(boolean... isClear) {
		return DAOimpl.getDAO(null, isClear);
	}

	static DAO getDAO(String homeDir, boolean... isClear) {
		return DAOimpl.getDAO(homeDir, isClear);
	}

}
