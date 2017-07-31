package sev.alvioneurope.ñoursemodel.impl;

import java.util.List;

import sev.alvioneurope.ñoursemodel.impl.StudyImpl.Session;

public interface Student extends DAOkeys {
	String getName();
	void   setName(String name);

	String getAddress();
	void   setAddress(String address);

	String getPhone();
	void   setPhone(String telephone);
	
	String getEmail();
	void   setEmail(String email);
	
	Integer getBookNum();
	void    setBookNum(int bookNum);
	
	float   getAverageScore();

	void addToCourse(Course course);
	List<Session> getListFinished();
	List<Session> getListPassing();


	static Student getNew(int bookNum, String name, String address, String phone, String email) {
		return new StudentImpl(bookNum, name, address, phone, email);
	}

	static Student getNew() {
		return new StudentImpl();
	}
	

}
