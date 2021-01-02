
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by TheEndl on 01.05.2017.
 */


public class Student{

    private String name;
    private String address;
    private String phone;
    private String mail;
    private Integer recordBookNumber;
    private float studyLevel;
    private Map<Course, Study> studentInfo = new HashMap<>();
    private ArrayList<Course> courses = new ArrayList<>();


    public Student(String name, String address, String phone, String mail, Integer recordBookNumber) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.mail = mail;
        this.recordBookNumber = recordBookNumber;
        this.studyLevel = studyLevel;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getRecordBookNumber() {
        return recordBookNumber;
    }

    public void setRecordBookNumber(Integer recordBookNumber) {
        this.recordBookNumber = recordBookNumber;
    }

    public float getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(float studyLevel) {

       this.studyLevel = studyLevel;

    }

    // Returns list of courses for this student
    public ArrayList<Course> getCourses() {
        return courses;
    }
    // See the students information about his study
    public void studyInfo(Course course, Study study)
    {
        this.studentInfo.put(course, study);
        this.courses.add(course);
    }


    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", mail='" + mail + '\'' +
                ", recordBookNumber=" + recordBookNumber +
                ", studyLevel=" + studyLevel +
                ", studentInfo=" + studentInfo +
                ", courses=" + courses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (Float.compare(student.studyLevel, studyLevel) != 0) return false;
        if (!name.equals(student.name)) return false;
        if (!address.equals(student.address)) return false;
        if (!phone.equals(student.phone)) return false;
        if (!mail.equals(student.mail)) return false;
        if (!recordBookNumber.equals(student.recordBookNumber)) return false;
        if (!studentInfo.equals(student.studentInfo)) return false;
        return courses.equals(student.courses);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + mail.hashCode();
        result = 31 * result + recordBookNumber.hashCode();
        result = 31 * result + (studyLevel != +0.0f ? Float.floatToIntBits(studyLevel) : 0);
        result = 31 * result + studentInfo.hashCode();
        result = 31 * result + courses.hashCode();
        return result;
    }
}
