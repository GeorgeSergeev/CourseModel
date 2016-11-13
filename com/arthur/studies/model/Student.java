package com.arthur.studies.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Student implements Serializable {
    private String name;
    private String address;
    private String phone;
    private String email;
    private Integer recordBookNum;
    private float averageLevel;

    private static int id;

    private List<Course> courses = new ArrayList<>();
    private List<PassingCourse> passing = new ArrayList<>();

    public Student(String name, String address, String phone, String email,
                   List<Course> courses) {
        // if inputs is incorrect
        if ((name == null) || (address == null) || (phone == null) ||
                (courses == null) || (courses.isEmpty()) || "".equals(name) ||
                "".equals(address) || "".equals(phone) || "".equals(email)) {
            throw new RuntimeException("Incorrect student inputs...");
        }
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        // start number for record-book is 1
        this.recordBookNum = ++id;
        // enroll to each course of the list
        for (Course c : courses) {
            this.enroll(c);
        }
    }

    public void enroll(Course course) {
        if ((course != null) && !courses.contains(course)) {
            courses.add(course);
            passing.add(new PassingCourse(course));
            course.addStudent(this);
        }
    }

    public void unroll(Course course) {
        if ((course == null) || !courses.contains(course)) {
            return;
        }
        PassingCourse pass = getPassingCourse(course);
        // if this courses is not completed (we can't unroll completed course)
        if (pass.getFinalScore() == null) {
            courses.remove(course);
            passing.remove(pass);
            course.removeStudent(this);
        }
    }

    public List<Course> listCompletedCourses() {
        List<Course> result = new ArrayList<>();
        for (PassingCourse pass : passing) {
            // if this is completed course
            // which has a final score
            if (pass.getFinalScore() != null) {
                result.add(pass.getCourse());
            }
        }
        return result;
    }

    public PassingCourse getPassingCourse(Course course) {
        if (course != null) {
            for (PassingCourse pass : passing) {
                if (course.equals(pass.getCourse())) {
                    return pass;
                }
            }
        }
        return null;
    }

    private void computeAverageLevel() {
        int sum = 0;
        // this variable counts completed courses
        int count = 0;
        for (PassingCourse pass : passing) {
            Integer score;
            // if this is completed courses
            if ((score = pass.getFinalScore()) != null) {
                sum += score;
                count++;
            }
        }
        averageLevel = (count > 0) ? ((1.0f * sum) / count) : 0;
    }

    @Override
    public String toString() {
        computeAverageLevel();
        return  "Student {name = " +
                name +
                "; address = " +
                address +
                "; phone = " +
                phone +
                "; email = " +
                email +
                "; recordBookNum = " +
                recordBookNum +
                "; averageLevel = " +
                averageLevel;
    }
}
