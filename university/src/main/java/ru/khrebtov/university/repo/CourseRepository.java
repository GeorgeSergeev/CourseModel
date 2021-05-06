package ru.khrebtov.university.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.khrebtov.university.entity.Course;
import ru.khrebtov.university.entity.Professor;
import ru.khrebtov.university.entity.Student;

import java.util.List;

@Repository
public class CourseRepository {

    private final CourseRepo courseRepo;

    @Autowired
    public CourseRepository(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    public Course findById(Long id) {
        return courseRepo.findById(id).orElse(null);
    }

    public Long count() {
        return courseRepo.count();
    }

    public void saveOrUpdate(Course course) {
        courseRepo.save(course);
    }

    public void deleteById(Long id) {
        courseRepo.deleteById(id);
    }

    public List<Professor> getCourseProfessor(Long id) {
        return courseRepo.getCourseProfessor(id);
    }

    public List<Student> getCourseStudents(Long id) {
        return courseRepo.getCourseStudents(id);
    }
}
