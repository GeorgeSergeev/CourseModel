package testgroup.model;

public class Lecturer extends Person {
    private int lecturerID;

    private float salary;

    public int getLecturerID() {
        return lecturerID;
    }

    public float getSalary() {
        return salary;
    }

    public void setLecturerID(int lecturerID) {
        this.lecturerID = lecturerID;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }
}


