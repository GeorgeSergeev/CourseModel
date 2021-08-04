package ru.softlab.coursemodel.repository;

import org.springframework.stereotype.Repository;
import ru.softlab.coursemodel.model.Student;

@Repository
public interface StudentRepository extends BaseEntityRepository<Student> {
}
