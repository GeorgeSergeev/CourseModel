package ru.softlab.coursemodel.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.softlab.coursemodel.model.Course;

import java.util.Collection;

@Repository
public interface CourseRepository extends BaseEntityRepository<Course> {


    @Modifying
    @Query(value = "INSERT INTO students_courses(student_id, course_id) VALUES(:studentId, :courseId)",
            nativeQuery = true)
    void bindStudentAndCourse(@Param("studentId") Integer studentId,
                              @Param("courseId") Integer courseId);

    @Modifying
    @Query(value = "DELETE FROM students_courses WHERE student_id = :studentId AND course_id = :courseId)",
            nativeQuery = true)
    void unbindStudentAndCourse(@Param("studentId") Integer studentId,
                                @Param("courseId") Integer courseId);

    @Query(value = "SELECT INTO students_courses(student_id, course_id) VALUES(:studentId, :courseId)",
            nativeQuery = true)
    Collection<Course> findAllByStudentIdAndFinalMarkIsNotNull(Integer studentId);
}
