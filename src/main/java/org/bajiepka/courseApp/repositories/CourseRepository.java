package org.bajiepka.courseApp.repositories;

import org.bajiepka.courseApp.domain.Course;
import org.springframework.data.repository.CrudRepository;

public interface CourseRepository extends CrudRepository<Course, Long> {
}
