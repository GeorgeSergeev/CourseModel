package ru.softlab.coursemodel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.softlab.coursemodel.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends BaseEntityRepository<Student> {

    @Query(value = "SELECT student_id FROM students_courses WHERE course_id = :courseId",
            nativeQuery = true)
    Collection<Integer> findAllByCourseId(@Param("courseId") Integer courseId);
}
