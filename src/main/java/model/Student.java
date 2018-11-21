package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@JsonAutoDetect
@Data
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "student_id")
    private int id;

    @Column (name = "name")
    private String name;

    @Column (name = "address")
    private String address;

    @Column (name = "phone")
    private String phone;

    @Column (name = "email")
    private String email;

    @Column (name = "grade_book_num")
    private int gradeBookNum;

    @SuppressWarnings("deprecation")
    @JsonUnwrapped
    @JsonManagedReference
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<StudentsGroup> courseStudentsGroups = new ArrayList<>();

    @SuppressWarnings("deprecation")
    @JsonUnwrapped
    @JsonManagedReference
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<Score> scores = new ArrayList<>();

    public void addToGroup(StudentsGroup group) {
        courseStudentsGroups.add(group);
    }

    public void remofeFromGroup(StudentsGroup group) {
        courseStudentsGroups.remove(group);
        List<Score> tmp = new ArrayList<>(scores);
        for (Score score :tmp) {
            if (score.getCourse().getId() == group.getCourse().getId()) {
                scores.remove(score);
            }
        }
    }

    public void addSCore(Score score) {
        scores.add(score);
    }

    public void removeScore(Score score) {
        scores.remove(score);
    }

    public Student() {
    }

    public Student(Student student) {
        this.name = student.getName();
        this.address = student.getAddress();
        this.phone = student.getPhone();
        this.email = student.getEmail();
        this.gradeBookNum = student.getGradeBookNum();
    }


    public Student(String name, String address, String phone, String email, int gradeBookNum) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.gradeBookNum = gradeBookNum;
    }

    public float calculateAverageScore() {
        float result = 0f;
        int i = 0;
        for (Score score :scores) {
            i += score.getScore();
        }
        result = 1.0f * i / scores.size();
        return result;
    }

    public float averageScoreForCourse(Course course) {
        float result = 0f;
        int sum = 0, count = 0;
        for (StudentsGroup studentsGroup :courseStudentsGroups) {
            if (studentsGroup.getCourse().getId() == course.getId()) {
                for (Score score :scores) {
                    if (score.getCourse().getId() == course.getId()) {
                        sum += score.getScore();
                        count++;
                    }
                }
            }
        }
        if (count > 0) {
            result = 1.0f * sum / count;
        }
        return result;
    }

    public List<Course> graduatedCourses() {
        List<Course> result = new ArrayList<>();
        for (StudentsGroup studentsGroup :courseStudentsGroups) {
            if (StudentStatus.GRADUATED.equals(studentsGroup.getStatus())) {
                result.add(studentsGroup.getCourse());
            }
        }
        return result;
    }

}
