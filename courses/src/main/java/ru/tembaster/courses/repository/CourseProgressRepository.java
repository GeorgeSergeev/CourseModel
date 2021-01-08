package ru.tembaster.courses.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tembaster.courses.model.CourseProgress;

@Repository
public interface CourseProgressRepository extends JpaRepository<CourseProgress, Integer> {
}
