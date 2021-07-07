package org.bajiepka.courseApp.repositories;

import org.bajiepka.courseApp.domain.CourseProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseProgressRepository extends JpaRepository<CourseProgress, Long> {
}
