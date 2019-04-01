package org.bajiepka.courseApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sun.istack.NotNull;
import lombok.Data;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Course {

    private @Id @GeneratedValue Long id;
    private @Version int version;

    private final int MAX_COURSES_PER_STUDENT = 3;
    private final int MODULES_PER_COURSE = 10;
    @NotNull
    private String name;
    @NotNull
    private int number;
    private float cost;
    private int modules;

    @OneToMany(targetEntity = Student.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> participators = new ArrayList();

    public Course() {
    }

    public Course(String name, int number, float cost) {
        this.name = name;
        this.number = number;
        this.cost = cost;
    }

   public void addParticipator(Student student){

    }

    public void removeParticipator(Student student){
        if (participators.contains(student)){
            participators.remove(student);
            System.out.println("Студент больше не будет прослушивать курс: " + this.name);
        } else {
            System.out.println("Не было такого студента на курсе...");
        }
    }

    private boolean addParticipators(Student participator) {
//        this.participators.add(participator);
//        participator.signUpForCourse(this);
        return true;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void fill(Course course) {
        this.version++;
        this.name = course.getName();
        this.modules = course.getModules();
        this.number = course.getNumber();
    }
}
