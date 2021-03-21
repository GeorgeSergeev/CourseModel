package ru.khrebtov.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khrebtov.entity.Course;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class CourseRepository {
    private static final Logger logger = LoggerFactory.getLogger(CourseRepository.class);

    @PersistenceContext(unitName = "ds")
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

    public Course findByName(int number) {
        return em.createNamedQuery("findCourseByNumber", Course.class)
                .setParameter("number", number)
                .getSingleResult();
    }
}
