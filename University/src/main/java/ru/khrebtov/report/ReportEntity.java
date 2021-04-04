package ru.khrebtov.report;

public class ReportEntity {
    private String professorName;
    private Long numberOfStudents;
    private Float averageStudentsRating;

    public ReportEntity(String professorName, Long numberOfStudents, Float averageStudentsRating) {
        this.professorName = professorName;
        this.numberOfStudents = numberOfStudents;
        this.averageStudentsRating = averageStudentsRating;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public Long getNumberOfStudents() {
        return numberOfStudents;
    }

    public void setNumberOfStudents(Long numberOfStudents) {
        this.numberOfStudents = numberOfStudents;
    }

    public Float getAverageStudentsRating() {
        return averageStudentsRating;
    }

    public void setAverageStudentsRating(Float averageStudentsRating) {
        this.averageStudentsRating = averageStudentsRating;
    }
}
