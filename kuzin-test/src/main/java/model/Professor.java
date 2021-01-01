package model;

import java.util.LinkedList;
import java.util.List;

public class Professor {

	private String name;
	private String address;
	private String telephone;
	private Double salary;
	
	private List<Course> corsesToTeach = new LinkedList<>();
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
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
	 * @param address the address to set
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
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	/**
	 * @return the salary
	 */
	public Double getSalary() {
		return salary;
	}
	/**
	 * @param salary the salary to set
	 */
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	/**
	 * @return the corsesToTeach
	 */
	public List<Course> getCorsesToTeach() {
		return corsesToTeach;
	}
	/**
	 * @param corsesToTeach the corsesToTeach to set
	 */
	public void setCorsesToTeach(List<Course> corsesToTeach) {
		this.corsesToTeach = corsesToTeach;
	}
	/**
	 * @param corsesToTeach the corsesToTeach to set
	 */
	public void addCorsesToTeach(Course corseToTeach) {
		this.corsesToTeach.add(corseToTeach);
	}
}
