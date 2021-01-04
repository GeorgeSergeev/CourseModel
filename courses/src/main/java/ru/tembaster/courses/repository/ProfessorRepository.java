package ru.tembaster.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tembaster.courses.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Professor p WHERE p.id=:id")
    int delete(@Param("id")int id);
}
