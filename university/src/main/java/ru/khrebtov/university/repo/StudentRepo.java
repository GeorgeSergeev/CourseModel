package ru.khrebtov.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.khrebtov.university.entity.Course;
import ru.khrebtov.university.entity.Student;
import ru.khrebtov.university.entity.StudyCourse;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {
    @Query("select sc from StudyCourse sc where sc.student.id = ?1")
    List<StudyCourse> getStudentStudyCourses(Long studentId);

    @Query("select c from Course c left join StudyCourse cs on c.id=cs.course.id where cs.student.id = ?1")
    List<Course> getStudentCourses(Long studentId);
}
