package ru.softlab.coursemodel.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.softlab.coursemodel.model.CompletingCourse;

import java.util.Collection;

@Repository
public interface CompletingCourseRepository extends BaseEntityRepository<CompletingCourse> {

    void deleteAllByStudentIdAndCourseId(Integer studentId, Integer courseId);

    @Query(value = "SELECT mark FROM completing_courses WHERE student_id = :studentId AND course_id = :courseId",
            nativeQuery = true)
    Collection<Integer> findAllMarksByStudentIdAndCourseId(Integer studentId, Integer courseId);

    @Query(value = "SELECT mark FROM completing_courses WHERE student_id = :studentId",
            nativeQuery = true)
    Collection<Integer> findAllMarksByStudentId(Integer studentId);

    @Modifying
    @Query(value = "UPDATE completing_courses SET final_mark = :finalMark WHERE student = :studentId AND course_id = :courseId",
            nativeQuery = true)
    void setMarkByStudentIdAndCourseId(@Param("studentId") Integer studentId,
                                       @Param("courseId") Integer courseId,
                                       @Param("mark") Float finalMark);

    @Query(value = """
            SELECT EXISTS(
            SELECT 1 FROM completing_courses
            WHERE student = :studentId AND course_id = :courseId AND final_mark IS NOT NULL)
            """, nativeQuery = true)
    boolean existsFinalMarkByStudentIdAndCourseId(@Param("studentId") Integer studentId,
                                                  @Param("courseId") Integer courseId);
}
