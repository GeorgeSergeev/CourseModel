package ru.latypov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.latypov.model.StatusKurs;

@Repository
public interface StatusKursRepository extends JpaRepository <StatusKurs, Integer> {
}
