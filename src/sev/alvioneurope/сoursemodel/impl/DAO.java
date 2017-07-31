package sev.alvioneurope.�oursemodel.impl;

import java.io.IOException;
import java.util.List;

import sev.alvioneurope.�oursemodel.impl.DAOservice.BuilderCourse;
import sev.alvioneurope.�oursemodel.impl.DAOservice.BuilderProfessor;
import sev.alvioneurope.�oursemodel.impl.DAOservice.BuilderStudent;
import sev.alvioneurope.�oursemodel.impl.StudyImpl.Session;

//��.������: "DAO". ������ � ��. ��������� ��� ��������
public interface DAO {
	//------------------- Create -------------------
	BuilderStudent		createStudent(Student student);
	BuilderCourse		createCourse(Course course);
	BuilderProfessor	createProfessor(Professor professor);
	
	//------------------- Read ----------------------
	Student			readStudent(int gradeBookNum);
	Course			readCourse(int numCourse);	
	Professor		readProfessor(String name, String address);
//	Session	readSession(Course course, Student student);  //1 ������� �� ���� ������� ������ + default ������ (create new)
//	Session	readSession(int keyPrime);					//2 ������� �� ��������� �����, �� ��� default ������
	
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
	
	//------------------- ������������� ��� StudyImpl --------------------
	StudyImpl readStudy();
	void updateStudy() throws IOException;

	static DAO getDAO(boolean... isClear) {
		return DAOimpl.getDAO(null, isClear);
	}

	static DAO getDAO(String homeDir, boolean... isClear) {
		return DAOimpl.getDAO(homeDir, isClear);
	}

}
