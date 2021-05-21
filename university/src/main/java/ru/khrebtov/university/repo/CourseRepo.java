package ru.khrebtov.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.khrebtov.university.entity.Course;
import ru.khrebtov.university.entity.Professor;
import ru.khrebtov.university.entity.Student;

import java.util.List;

public interface CourseRepo extends JpaRepository<Course, Long> {
    @Query("select p from Professor p left join CourseProfessor cp on p.id=cp.professorsId where cp.courseId = ?1")
    List<Professor> getCourseProfessor(Long id);

    @Query("select s from Student s left join StudyCourse cs on s.id=cs.student.id where cs.course.id = ?1")
    List<Student> getCourseStudents(Long id);
}
