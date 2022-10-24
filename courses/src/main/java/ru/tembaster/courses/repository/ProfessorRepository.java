package ru.tembaster.courses.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tembaster.courses.model.Professor;
import ru.tembaster.courses.model.Student;

import java.util.List;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Integer> {

    @Query("SELECT DISTINCT s FROM Student s " +
           "JOIN CourseProgress cp ON cp.student = s " +
           "JOIN Course c ON cp.course = c " +
           "JOIN Professor p ON p.course = c " +
           "WHERE p.id=:id")
    List<Student> getAllStudentsByProfessorId(@Param("id")int id);


//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Professor p WHERE p.id=:id")
//    int delete(@Param("id")int id);
}