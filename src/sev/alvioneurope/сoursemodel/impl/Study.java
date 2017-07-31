package sev.alvioneurope.ñoursemodel.impl;

import java.util.List;

public interface Study {

	void setScore(Student student, Course course, int score);

	void setScoreFinal(Student student, Course course, int score);

	<T> List<T> getListSessionAll();

	<T> List<T> getListStudentAll();

	<T> List<T> getListCourseAll();

	String toString();

	static Study getNew() {
		return StudyImpl.getStudy();
	}
	

}