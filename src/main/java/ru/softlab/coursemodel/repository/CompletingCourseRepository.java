package ru.softlab.coursemodel.repository;

import org.springframework.stereotype.Repository;
import ru.softlab.coursemodel.model.CompletingCourse;
import ru.softlab.coursemodel.model.Course;
import ru.softlab.coursemodel.model.Student;

import java.util.Collection;

@Repository
public interface CompletingCourseRepository extends BaseEntityRepository<CompletingCourse> {

    Collection<Integer> findAllMarksByStudentAndCourse(Student student, Course course);

    Collection<Integer> findAllMarksByStudent(Student student);
}
