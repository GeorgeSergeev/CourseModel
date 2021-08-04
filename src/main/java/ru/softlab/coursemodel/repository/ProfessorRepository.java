package ru.softlab.coursemodel.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.softlab.coursemodel.model.Professor;

@Repository
public interface ProfessorRepository extends BaseEntityRepository<Professor> {

    @Query(value = """
            SELECT p.name professor_name
            , COUNT(c_c.student_id) students_amount
            , ROUND(AVG(c_c.mark), 2) common_average_performance
            FROM professors p
            LEFT JOIN professors_courses p_c ON p.id = p_c.professor_id
            LEFT JOIN courses c ON p_c.course_id = c.id
            LEFT JOIN completing_courses c_c ON c_c.course_id = c.id
            GROUP BY professor_name
            ORDER BY professor_name
            """, nativeQuery = true)
    Object[] findAllProfessorReports();
}
