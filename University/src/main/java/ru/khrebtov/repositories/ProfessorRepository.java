package ru.khrebtov.repositories;

import ru.khrebtov.entity.Professor;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ProfessorRepository {

    @PersistenceContext(unitName = "ds")
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

}
