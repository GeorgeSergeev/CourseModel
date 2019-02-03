package model.uml;

public class ProfessorException extends Exception {
	private final String text;

	public ProfessorException(String text) {
		this.text = text;
	}

	public String toString() {
		return text;
	}
}
