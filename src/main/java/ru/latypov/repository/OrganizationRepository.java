package ru.latypov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.latypov.model.Organization;
@Repository
public interface OrganizationRepository extends JpaRepository <Organization, Integer> {
}
