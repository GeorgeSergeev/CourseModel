package com.rstyle.softlab.models;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class CourseResults {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String marks;
	
	@ManyToOne
    @JoinColumn(name = "student_id")
	private Student student;
	
	@ManyToOne
    @JoinColumn(name = "course_id", insertable=false, updatable=false)
	private Course course;
	
	@Transient
	private List<Float> floatMarks;
	
	public Float getCurrentAverage() {
		Optional<Float> average =  getListMarks().stream().reduce((x, x1) -> x + x1);
		return average.isPresent() ? (average.get()/floatMarks.size()) : 0.0f;
	}
	
	public void getFinalMark() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getMarks() {
		return marks;
	}

	public void setMarks(String marks) {
		this.marks = marks;
		floatMarks = convertMarks(marks);
	}

	public List<Float> getListMarks() {
		return floatMarks == null || floatMarks.size()==0 ? (floatMarks = convertMarks(marks)) : floatMarks;
	}

	public void setListMarks(List<Float> floatMarks) {
		this.floatMarks = floatMarks;
		marks = convertFloatMarks(floatMarks);
	}
	
	private List<Float> convertMarks(String marks){
		return Stream.of(marks.split("\\s+")).map(x -> Float.parseFloat(x)).collect(Collectors.toList());
	}
	
	private String convertFloatMarks(List<Float> floatMarks) {
		return floatMarks.stream().map(x -> String.valueOf(x)).collect(Collectors.joining(" "));
	}
}
