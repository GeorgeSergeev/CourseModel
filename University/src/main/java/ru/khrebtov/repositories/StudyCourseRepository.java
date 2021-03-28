package ru.khrebtov.repositories;

import ru.khrebtov.entity.StudyCourse;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StudyCourseRepository {

    @PersistenceContext(unitName = "ds")
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
}
