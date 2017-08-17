package model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Generated;

public class Student {

	public String name;
	private String address;
	private String telephone;
	private String email;
	private Integer scoreBookNum;

	@Generated(value = { "0.0" })
	private double midScore;
	

	private List<CourseFlow> courseFlow = new LinkedList<>();

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the telephone
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone
	 *            the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the scoreBookNum
	 */
	public Integer getScoreBookNum() {
		return scoreBookNum;
	}

	/**
	 * @param scoreBookNum
	 *            the scoreBookNum to set
	 */
	public void setScoreBookNum(Integer scoreBookNum) {
		this.scoreBookNum = scoreBookNum;
	}

	/**
	 * @return the midScore
	 */
	public double getMidScore() {
		midScore = 0;

		for (CourseFlow c : courseFlow) {
			midScore += c.getMidScore();
		}

		return midScore / courseFlow.size();
	}

	/**
	 * @param midScore
	 *            the midScore to set
	 */
	public void setMidScore(double midScore) {
		this.midScore = midScore;
	}

	// По заданию следующие два метода должны были возвращать void----
	// в случае выполнения задания в консоли это имело бы смысл?
	// можно было бы вывести данные в консоль.
	// НО так как приложение имеет интерфейс куда логичней
	// возвращать значения в контроллер который в послдствии обработает
	// и выведет необходимые данные.

	// Условия не было дано поэтому
	// предположительно студент может продолжить записываться
	// если его середний бал выше трёх
	public boolean canEnroll() {
		return midScore > 3.0;
	}

	public Set<Course> getCourseList() {
		Set<Course> courseList = new HashSet<>();
		
		for(CourseFlow cf :this.courseFlow){
			courseList.add(cf.getCourse());
		}
		
		return courseList;
	}
	// ------------------------------------------------------------------

	public List<CourseFlow> getCourseFlow() {
		return courseFlow;
	}

	public void setCourseFlow(List<CourseFlow> courseFlow) {
		this.courseFlow = courseFlow;
	}
	
	public void addCourseFlow(CourseFlow courseFlow) {
		this.courseFlow.add(courseFlow);
	}
}
