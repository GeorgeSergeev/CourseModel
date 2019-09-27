package ru.sorochinsky.repositiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.sorochinsky.model.Professor;

/**
 * Repository class for {@link ru.sorochinsky.model.Professor}
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

// This will be AUTO IMPLEMENTED by Spring into a Bean called professorRepository
// CRUD refers Create, Read, Update, Delete

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    @Query("select s from Professor s where s.name = :name")
    Professor findByName(@Param("name") String name);

}
