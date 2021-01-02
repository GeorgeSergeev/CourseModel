package com.rstyle.softlab.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Student {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String address;
	private String phone;
	private String email;
	private Integer studentRecordBookNumber;
	private Float average;
	
	@OneToMany(mappedBy = "student", fetch=FetchType.LAZY)
	private Set<CourseResults> courseResults;
	
	public void getCompletedCourses() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getStudentRecordBookNumber() {
		return studentRecordBookNumber;
	}

	public void setStudentRecordBookNumber(Integer studentRecordBookNumber) {
		this.studentRecordBookNumber = studentRecordBookNumber;
	}

	public Float getAverage() {
		return average;
	}

	public void setAverage(Float average) {
		this.average = average;
	}

	public Set<CourseResults> getCourseResults() {
		return courseResults;
	}

	public void setCourseResults(Set<CourseResults> courseResults) {
		this.courseResults = courseResults;
	}
	
	
}
