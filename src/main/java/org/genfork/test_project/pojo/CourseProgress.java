package org.genfork.test_project.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * User: <a href="http://gencloud.solutions">GenCloud</a>
 * Date: 2017/07
 */
public class CourseProgress implements Serializable {
	private boolean isComplete;

	private List<Integer> raiting = new ArrayList<>();

	public CourseProgress(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public double getAverangeRating() {
		return raiting.stream().mapToInt(e -> e).average().getAsDouble();
	}

	public boolean addRating(int rating) {
		return raiting.add(rating);
	}

	public boolean isComplete() {
		return isComplete;
	}

	@Override
	public String toString() {
		return "CourseProgress{" +
				"isComplete=" + isComplete +
				", raiting=" + raiting +
				'}';
	}
}
