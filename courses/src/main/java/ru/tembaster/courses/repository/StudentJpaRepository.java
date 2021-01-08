package ru.tembaster.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tembaster.courses.model.Course;
import ru.tembaster.courses.model.Student;

import java.util.List;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Integer> {

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Student s WHERE s.id=:id")
//    int delete(@Param("id")int id);

    @Query("SELECT cp.mark FROM CourseProgress cp WHERE cp.student=:id")
    List<Integer> getAllMarksByStudentId(Integer id);

    @Query("SELECT DISTINCT c FROM Student s " +
     "JOIN CourseProgress cp ON cp.student = s " +
      "JOIN Course c ON cp.course = c WHERE s.id=:id")
    List<Course> getWithCourses(@Param("id")Integer id);
}
