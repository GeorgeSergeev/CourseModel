package sev.alvioneurope.сoursemodel.impl;

//ДБ.Обьект: "Профессор"
class ProfessorImpl implements Professor {
	//доменные
	private String name;
	private String address;
	private String phone;
	private Float  payment;
	//служебные
	int meterFree;	//Контрольный счётчик курсов, на которых преподаёт 

	ProfessorImpl(){
		meterFree = 0;
	}

	@Override
	public String getName() {
		return  name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String getPhone() {
		return phone;
	}

	@Override
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public void setPayment(float payment) {
		this.payment = payment;
	}

	@Override
	public float getPayment() {
		return payment;
	}
	
	@Override
	public int hashCode() {
		return hashCodeProfessor(name, address);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (!(obj instanceof ProfessorImpl))
			return false;
		ProfessorImpl other = (ProfessorImpl) obj;
		if (name != other.name || address != other.address)	
			return false;
		return true;
	}

	@Override
	public String toString() {
		String result;
		result = String.format("\nПреподаватель:\n\tимя: %s\n\tадрес: %s\n\tтелефон: %s\n\tоплата: %.2f руб.\n\tУчастие на курсах: "+
												((meterFree == 0)?"свободен\n":"преподаёт на %d курсах\n"),
												name,address,phone,payment,meterFree);
		return result;
	}
}
