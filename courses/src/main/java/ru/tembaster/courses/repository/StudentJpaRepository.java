package ru.tembaster.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.tembaster.courses.model.Student;

import java.util.List;

@Repository
public interface StudentJpaRepository extends JpaRepository<Student, Integer> {

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Student s WHERE s.id=:id")
//    int delete(@Param("id")int id);

    @Query("SELECT cp.mark FROM CourseProgress cp WHERE cp.student =: id")
    List<Integer> getAllMarksByStudentId(Integer id);
}
