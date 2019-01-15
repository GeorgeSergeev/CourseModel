package ru.latypov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.latypov.model.Office;
@Repository
public interface OfficeRepository extends JpaRepository <Office, Integer> {
}
