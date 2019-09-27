package ru.sorochinsky.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sorochinsky.model.Course;

/**
 * Repository class for {@link ru.sorochinsky.model.Course}
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

// This will be AUTO IMPLEMENTED by Spring into a Bean called courseRepository
// CRUD refers Create, Read, Update, Delete

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select s from Course s where s.name = :name")
    Course findByName(@Param("name") String name);

}
