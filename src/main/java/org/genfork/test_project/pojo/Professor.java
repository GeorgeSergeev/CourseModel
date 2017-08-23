package org.genfork.test_project.pojo;

import java.io.Serializable;

/**
 * User: <a href="http://gencloud.solutions">GenCloud</a>
 * Date: 2017/07
 */
public class Professor implements Serializable {
	private String name;
	private String address;
	private String telephone;
	private float payment;

	public Professor(String name, String address, String telephone, float payment) {
		this.name = name;
		this.address = address;
		this.telephone = telephone;
		this.payment = payment;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getTelephone() {
		return telephone;
	}

	public float getPayment() {
		return payment;
	}

	@Override
	public String toString() {
		return "Профессор: " + name + " Адресс: " + address + " Телефон: " + telephone + " Стоимость: " + payment;
	}
}