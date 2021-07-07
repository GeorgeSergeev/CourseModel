package org.bajiepka.courseApp.wrappers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import org.bajiepka.courseApp.domain.Course;
import org.bajiepka.courseApp.domain.CourseProgress;
import org.bajiepka.courseApp.domain.Professor;
import org.bajiepka.courseApp.domain.Student;

@JsonRootName("import")
public class RootHolder {

    private final Iterable<Student> students;
    public final Iterable<Course> courses;
    public final Iterable<CourseProgress> courseProgresses;
    public final Iterable<Professor> professors;

    @JsonCreator
    public RootHolder(@JsonProperty("student") final Iterable<Student> students,
                      @JsonProperty("course") final Iterable<Course> courses,
                      @JsonProperty("courseProgress") final Iterable<CourseProgress> courseProgresses,
                      @JsonProperty("professor") final Iterable<Professor> professors){

        this.students = students;
        this.courses = courses;
        this.courseProgresses = courseProgresses;
        this.professors = professors;
    }

    @JsonProperty("student")
    public Iterable<Student> getStudents(){
        return students;
    }

    @JsonProperty("course")
    public Iterable<Course> getCourses(){
        return courses;
    }

    @JsonProperty("courseProgress")
    public Iterable<CourseProgress> getCourseProgresses(){
        return courseProgresses;
    }

    @JsonProperty("professor")
    public Iterable<Professor> getProfessors(){
        return professors;
    }
}
