package ru.tembaster.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tembaster.courses.model.Student;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Student s WHERE s.id=:id")
//    int delete(@Param("id")int id);
    @Query("SELECT p.mark FROM CourseProgress p WHERE p.student =: studentId AND p.course =: courseId")
    List<Integer> getAllMarkByStudentIdAndCourseId(Integer studentId, Integer courseId);
}
