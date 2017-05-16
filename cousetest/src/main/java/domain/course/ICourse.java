package domain.course;


import com.sun.istack.internal.NotNull;
import domain.Student;

public interface ICourse {
    void addStudent(Student student);
    void removeStudent(Student student);
}
