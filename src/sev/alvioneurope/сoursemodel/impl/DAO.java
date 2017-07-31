package sev.alvioneurope.сoursemodel.impl;

import java.io.IOException;
import java.util.List;

import sev.alvioneurope.сoursemodel.impl.DAOservice.BuilderCourse;
import sev.alvioneurope.сoursemodel.impl.DAOservice.BuilderProfessor;
import sev.alvioneurope.сoursemodel.impl.DAOservice.BuilderStudent;
import sev.alvioneurope.сoursemodel.impl.StudyImpl.Session;

//ДБ.Обьект: "DAO". Доступ к БД. Интерфейс для клиентов
public interface DAO {
	//------------------- Create -------------------
	BuilderStudent		createStudent(Student student);
	BuilderCourse		createCourse(Course course);
	BuilderProfessor	createProfessor(Professor professor);
	
	//------------------- Read ----------------------
	Student			readStudent(int gradeBookNum);
	Course			readCourse(int numCourse);	
	Professor		readProfessor(String name, String address);
//	Session	readSession(Course course, Student student);  //1 вариант по двум внешним ключам + default случай (create new)
//	Session	readSession(int keyPrime);					//2 вариант по основному ключу, но без default случая
	
	//------------------- ReadAll ------------------
	List<Student>	 	readStudentAll();
	List<Course>		readCourseAll();
	List<Professor>		readProfessorAll();
	//List<Session>	readCoursePassingAll();
	
	//------------------- Update --------------------
	void	updateStudent(Student student);
	void	updateCourse(Course course);
	void	updateProfessor(Professor professor);

	//------------------- Delete --------------------
	void deleteStudent(Student student);
	void deleteCourse(Course course);
	void deleteProfessor(Professor professor);
//	void deleteSession(Session coursePas);
	
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
