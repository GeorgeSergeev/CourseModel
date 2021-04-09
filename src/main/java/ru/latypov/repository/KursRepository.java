package ru.latypov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.latypov.model.Kurs;
@Repository
public interface KursRepository extends JpaRepository <Kurs, Integer> {
}
