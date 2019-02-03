package model.uml;

/**
 * Класс исключительно для "обслуживания" классов
 * Student и Professor
 * @author Arsen Pan
 *
 */
public abstract class Man {
	protected final String name;
	private final String address;
	private final String phone;

	public abstract String getName();

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	protected Man(String name, String address, String phone) {
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
}
