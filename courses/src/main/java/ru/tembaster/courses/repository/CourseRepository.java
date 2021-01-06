package ru.tembaster.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tembaster.courses.model.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Course c WHERE c.id=:id")
//    int delete(@Param("id") int id);
}
