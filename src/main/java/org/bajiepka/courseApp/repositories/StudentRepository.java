package org.bajiepka.courseApp.repositories;

import org.bajiepka.courseApp.domain.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, Long> {
}
