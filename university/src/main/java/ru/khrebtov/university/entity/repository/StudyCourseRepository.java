package ru.khrebtov.university.entity.repository;


import org.springframework.stereotype.Repository;
import ru.khrebtov.university.entity.Course;
import ru.khrebtov.university.entity.Student;
import ru.khrebtov.university.entity.StudyCourse;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class StudyCourseRepository {

    @PersistenceContext
    private EntityManager em;


    public List<StudyCourse> findAll() {
        return em.createNamedQuery("findAllStudyCourse", StudyCourse.class)
                .getResultList();
    }

    public StudyCourse findById(Long id) {
        return em.createNamedQuery("findStudyCourseById", StudyCourse.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Long countAll() {
        return em.createNamedQuery("countAllStudyCourse", Long.class)
                .getSingleResult();
    }

    public void saveOrUpdate(StudyCourse studyCourse) {
        if (studyCourse.getId() == null) {
            em.persist(studyCourse);
        }
        em.merge(studyCourse);
    }

    public void deleteById(Long id) {
        em.createNamedQuery("deleteStudyCourseById")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Double getAverageRating(Long studyCourseId) {
        return em.createNamedQuery("getAverageRating", Double.class)
                .setParameter("id", studyCourseId)
                .getSingleResult();
    }

    public List<Integer> getRatings(Long studyCourseId) {
        return em.createNamedQuery("getRatings", Integer.class)
                .setParameter("id", studyCourseId)
                .getResultList();
    }

    public Course getCourseByStudyCourseId(Long studyCourseId) {
        return em.createNamedQuery("getCourseByStudyCourseId", Course.class)
                .setParameter("id", studyCourseId)
                .getSingleResult();
    }

    public Student getStudentByStudyCourseId(Long studyCourseId) {
        return em.createNamedQuery("getStudentByStudyCourseId", Student.class)
                .setParameter("id", studyCourseId)
                .getSingleResult();
    }

    public StudyCourse findByCourseIdAndStudentId(Long courseId, Long studentId) {
        return em.createNamedQuery("findByCourseIdAndStudentId", StudyCourse.class)
                .setParameter("courseId", courseId)
                .setParameter("studentId", studentId)
                .getSingleResult();

    }
}
