package ru.son1q.main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PassingCours implements Serializable {
	private List<Integer> listEvaluation; 
	private Course currentCourse;

	public PassingCours(Course course) {
		if (course == null) 
			throw new RuntimeException("Объект 'course' пустой.");
		listEvaluation = new ArrayList<Integer>();
		currentCourse = course;
	}

	public float getAverageEvalution() {
		int sum = 0;
		float average = 0f;
		for(Integer i : listEvaluation)
			sum += i;
		try {
			average = sum / listEvaluation.size();
		} catch (ArithmeticException e) { 
			average = 0f;
		}
		return average;
	}
	
	//Добавление оценок
	public void addEvaluation(Student student, Integer evaluation) {
		if (ValidFormat.isValidEvaluation(evaluation))
			listEvaluation.add(evaluation);
		student.getAverageEvaluation();
	}

	public int getFinalEvaluation() {
		return Math.round(getAverageEvalution());
	}

	public List<Integer> getListEvaluation() {
		return listEvaluation;
	}
	
	public Course getCurrentCourse() {
		return currentCourse;
	}

	@Override public String toString() {
		return listEvaluation.toString();
	}

}
