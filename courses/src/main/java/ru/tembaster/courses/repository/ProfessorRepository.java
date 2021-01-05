package ru.tembaster.courses.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.tembaster.courses.model.Professor;

@Repository
public interface ProfessorRepository extends CrudRepository<Professor, Integer> {

//    @Modifying
//    @Transactional
//    @Query("DELETE FROM Professor p WHERE p.id=:id")
//    int delete(@Param("id")int id);
}
