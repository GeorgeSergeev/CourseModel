package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class CourseFlow {

	private String courseFlowId;
	private Course course;
	private List<Integer> scores = new LinkedList<>();

	public double getMidScore() {
		int sum = 0;

		for (Integer score : scores) {
			sum += score;
		}
		if (scores.size() != 0) {
			return (sum / scores.size());
		} else {
			return 0;
		}
	}

	public Integer getFinalScore(String student) {

		return scores.get(scores.size() - 1);
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return the course
	 */
	public List<Integer> getScores() {
		return this.scores;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}

	public void addScore(Integer score) {
		this.scores.add(score);
	}

	/**
	 * @return the courseFlowId
	 */
	public String getCourseFlowId() {
		return courseFlowId;
	}

	/**
	 * @param courseFlowId
	 *            the courseFlowId to set
	 */
	public void setCourseFlowId(String courseFlowId) {
		this.courseFlowId = courseFlowId;
	}

	public void generateId() {
		this.courseFlowId = String.valueOf(new Random().nextInt(1000));
	}

	public void output() {
		System.out.println(this.getCourseFlowId());
		System.out.println(this.getCourse().getName());
	}

}
