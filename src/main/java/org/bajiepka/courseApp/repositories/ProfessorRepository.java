package org.bajiepka.courseApp.repositories;

import org.bajiepka.courseApp.domain.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
