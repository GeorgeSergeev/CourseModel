package ru.softlab.coursemodel.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.softlab.coursemodel.model.Student;

@Repository
public interface StudentRepository extends BaseEntityRepository<Student> {

    @Modifying
    @Query(value = "INSERT INTO completing_courses(student_id, course_id, mark) VALUES(:studentId, :courseId, :mark)",
            nativeQuery = true)
    void setMarkForStudentByCourse(@Param("studentId") Integer studentId,
                                   @Param("courseId") Integer courseId,
                                   @Param("mark") Integer mark);
}
