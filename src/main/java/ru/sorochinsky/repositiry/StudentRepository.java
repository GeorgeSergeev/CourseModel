package ru.sorochinsky.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sorochinsky.model.Student;

/**
 * Repository class for {@link ru.sorochinsky.model.Student}
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

// This will be AUTO IMPLEMENTED by Spring into a Bean called studentRepository
// CRUD refers Create, Read, Update, Delete

public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("select s from Student s where s.name = :name")
    Student findByName(@Param("name") String name);

}