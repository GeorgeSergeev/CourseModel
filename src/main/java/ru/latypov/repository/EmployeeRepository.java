package ru.latypov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.latypov.model.Employee;
@Repository
public interface   EmployeeRepository  extends JpaRepository <Employee, Integer> {
}
