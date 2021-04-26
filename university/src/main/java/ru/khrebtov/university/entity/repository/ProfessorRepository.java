package ru.khrebtov.university.entity.repository;


import org.springframework.stereotype.Repository;
import ru.khrebtov.university.entity.Course;
import ru.khrebtov.university.entity.Professor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ProfessorRepository {

    @PersistenceContext
    private EntityManager em;


    public List<Professor> findAll() {
        return em.createNamedQuery("findAllProfessors", Professor.class)
                .getResultList();
    }

    public Professor findById(Long id) {
        return em.createNamedQuery("findProfessorById", Professor.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Long countAll() {
        return em.createNamedQuery("countAllProfessors", Long.class)
                .getSingleResult();
    }

    public void saveOrUpdate(Professor professor) {
        if (professor.getId() == null) {
            em.persist(professor);
        }
        em.merge(professor);
    }

    public void deleteById(Long id) {
        em.createNamedQuery("deleteProfessorsById")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Set<Course> getProfessorCourse(Long professorId) {
        return new HashSet<>(em.createNamedQuery("getProfessorCourse", Course.class)
                .setParameter("professorId", professorId)
                .getResultList());
    }

}
