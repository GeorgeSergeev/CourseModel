package testgroup.model;

import java.util.List;

public class CompletionOfCourse {
    private Student student;
    private List<Integer> studentScores;

    public Student getStudent() {
        return student;
    }

    public List<Integer> getStudentScores() {
        return studentScores;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setStudentScores(List<Integer> studentScores) {
        this.studentScores = studentScores;
    }
}
