package ru.latypov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.latypov.model.Student;
@Repository
public interface StudentRepository extends JpaRepository <Student, Integer> {
}
