package sev.alvioneurope.coursemodel.impl;

public interface Professor  extends DAOkeys {
	String getName();
	void   setName(String name);

	String getAddress();
	void   setAddress(String address);

	String getPhone();
	void   setPhone(String telephone);
	
	void  setPayment(float payment);
	float getPayment();

	static Professor  getNew() {
		return new ProfessorImpl();
	}

}
