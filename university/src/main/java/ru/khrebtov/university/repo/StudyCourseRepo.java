package ru.khrebtov.university.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.khrebtov.university.entity.Course;
import ru.khrebtov.university.entity.Student;
import ru.khrebtov.university.entity.StudyCourse;

import java.util.List;

public interface StudyCourseRepo extends JpaRepository<StudyCourse, Long> {

    @Query("select r.rating from Rating r where r.studyCourseId=?1")
    List<Integer> getRatings(Long id);

    @Query("select s from Student s left join StudyCourse sc on s.id=sc.student.id where sc.id = ?1")
    Student getStudentByStudyCourseId(Long id);

    @Query("select c from Course c left join StudyCourse sc on c.id=sc.course.id where sc.id = ?1")
    Course getCourseByStudyCourseId(Long id);

    @Query("select 1.0*sum(r.rating)/count(r) from Rating r where r.studyCourseId=?1")
    Double getAverageRating(Long id);

    @Query("select sc from StudyCourse sc where sc.course.id=?1 AND sc.student.id = ?2")
    StudyCourse findByCourseIdAndStudentId(Long courseId, Long studentId);
}
