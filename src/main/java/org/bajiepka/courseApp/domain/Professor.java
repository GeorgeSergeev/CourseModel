package org.bajiepka.courseApp.domain;
import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Professor {

    private @Id @GeneratedValue Long id;
    private @Version int version;
    @NotNull
    private String name;
    private String address;
    private String phone;
    private float salary;

    @ManyToMany(targetEntity = Course.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="PROFESSORS_COURSES",
            joinColumns = @JoinColumn( name="PROFESSOR_ID"),
            inverseJoinColumns = @JoinColumn( name="COURSE_ID")
    )
    private Set<Course> courses;

    public Professor() {
    }
}
