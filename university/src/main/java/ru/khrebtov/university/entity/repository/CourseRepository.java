package ru.khrebtov.university.entity.repository;


import org.springframework.stereotype.Repository;
import ru.khrebtov.university.entity.Course;
import ru.khrebtov.university.entity.Professor;
import ru.khrebtov.university.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class CourseRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Course> findAll() {
        return em.createNamedQuery("findAllCourse", Course.class)
                .getResultList();
    }

    public Course findById(Long id) {
        return em.createNamedQuery("findCourseById", Course.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Long countAll() {
        return em.createNamedQuery("countAllCourse", Long.class)
                .getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Course course) {
        if (course.getId() == null) {
            em.persist(course);
        }
        em.merge(course);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createNamedQuery("deleteCourseById")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Set<Student> getCourseStudents(Long courseId) {
        return new HashSet<>(em.createNamedQuery("getCourseStudents", Student.class)
                .setParameter("courseId", courseId).getResultList());

    }
    public Long getCountCourseStudents(Long courseId) {
        return em.createNamedQuery("getCountCourseStudents", Long.class)
                .setParameter("courseId", courseId).getSingleResult();

    }

    public Set<Professor> getCourseProfessor(Long courseId) {
        return new HashSet<>(em.createNamedQuery("getCourseProfessor", Professor.class)
                .setParameter("courseId", courseId).getResultList());
    }
}
