package com.arthur.studies.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Course implements Serializable {
    private String name;
    private int courseNumber;
    private float cost;

    private List<Professor> professors = new ArrayList<>();
    private List<Student> students = new ArrayList<>();

    public Course(String name, int courseNumber, float cost) {
        // if inputs is incorrect
        if ((name == null) || (courseNumber < 1) || (cost < 0) || "".equals(name)) {
            throw new RuntimeException("Incorrect course inputs...");
        }
        this.name = name;
        this.courseNumber = courseNumber;
        this.cost = cost;
    }

    public void addProfessor(Professor professor) {
        if ((professor != null) && !professors.contains(professor)) {
            professors.add(professor);
            professor.addCourse(this);
        }
    }

    public void removeProfessor(Professor professor) {
        if ((professor != null) && professors.contains(professor)) {
            professors.remove(professor);
            professor.removeCourse(this);
        }
    }

    public void addStudent(Student student) {
        if ((student != null) && !students.contains(student)) {
            students.add(student);
        }
    }

    public void removeStudent(Student student) {
        if ((student != null) && students.contains(student)) {
            students.remove(student);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (this.getClass() != obj.getClass()) {
            return false;
        }
        Course course = (Course) obj;

        return  this.name.equals(course.name) &&
                this.courseNumber == course.courseNumber &&
                Math.abs(this.cost - course.cost) < 1e-6;
    }

    @Override
    public String toString() {
        return  "Course {name = " +
                name +
                "; courseNumber = " +
                courseNumber +
                "; cost = " +
                cost +
                "}";
    }
}
