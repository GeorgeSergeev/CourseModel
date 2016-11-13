package com.arthur.studies.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Professor implements Serializable {
    private String name;
    private String address;
    private String phone;
    private Float salary;

    private List<Course> courses = new ArrayList<>();

    public Professor(String name, String address, String phone, float salary) {
        // if inputs is incorrect throw an exception
        if ((name == null) || (address == null) || (phone == null) || (salary < 0) ||
                "".equals(name) || "".equals(address) || "".equals(phone)) {
            throw new RuntimeException("Incorrect professor inputs...");
        }
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }

    void addCourse(Course course) {
        if ((course != null) && !courses.contains(course)) {
            courses.add(course);
        }
    }

    void removeCourse(Course course) {
        if ((course != null) && courses.contains(course)) {
            courses.remove(course);
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
        Professor professor = (Professor) obj;

        return  this.name.equals(professor.name) &&
                this.address.equals(professor.address) &&
                this.phone.equals(professor.phone) &&
                Math.abs(this.salary - professor.salary) < 1e-6;
    }

    @Override
    public String toString() {
        return  "Professor {name = " +
                name +
                "; address = " +
                address +
                "; phone = " +
                phone +
                "; salary = " +
                salary +
                "}";
    }
}
