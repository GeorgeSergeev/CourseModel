package exception;

public class NoSuchStudentOnCourse extends RuntimeException {
    public NoSuchStudentOnCourse(String message) {
        super(message);
    }
}
