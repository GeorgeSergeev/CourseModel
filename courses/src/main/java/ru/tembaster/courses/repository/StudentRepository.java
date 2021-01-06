package ru.tembaster.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tembaster.courses.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Student s WHERE s.id=:id")
//    int delete(@Param("id")int id);
}
