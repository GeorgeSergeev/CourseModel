package ru.softlab.coursemodel.repository;

import org.springframework.stereotype.Repository;
import ru.softlab.coursemodel.model.Course;

@Repository
public interface CourseRepository extends BaseEntityRepository<Course> {
}
