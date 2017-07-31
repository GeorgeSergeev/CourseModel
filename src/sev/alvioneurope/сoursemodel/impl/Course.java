package sev.alvioneurope.ñoursemodel.impl;

import java.util.List;

import sev.alvioneurope.ñoursemodel.impl.StudyImpl.Session;

public interface Course extends DAOkeys {

	String getName();
	void   setName(String name);

	float getCost();
	void  setCost(float cost);

	int		getNumber();
	void	setNumber(int number);
	
	void 	addProfessor(Professor prof);
	void	delProfessor(Professor prof);
	
	boolean actAddToCourse(Student student);
	boolean actRemoveFromCourse(Student student);
	
	List<Session> getListSessions();
	List<ProfessorImpl> getListProfessor();

	static Course getNew(int number, String name, float cost) {
		return new CourseImpl(number, name, cost);
	}

	static Course getNew() {
		return new CourseImpl();
	}
}
