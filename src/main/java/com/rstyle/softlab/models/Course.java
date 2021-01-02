package com.rstyle.softlab.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long course_id;
	private String name;
	private Integer number;
	private Float price;
	
	@ManyToOne
	@JoinColumn(name="professor_id", nullable=true)
	private Professor professor;
	
	@OneToMany(mappedBy = "course")
	private Set<CourseResults> courseResults;
	
	
	public Long getCourse_id() {
		return course_id;
	}

	public void setCourse_id(Long course_id) {
		this.course_id = course_id;
	}

	public Set<CourseResults> getCourseResults() {
		return courseResults;
	}

	public void setCourseResults(Set<CourseResults> courseResults) {
		this.courseResults = courseResults;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
}
