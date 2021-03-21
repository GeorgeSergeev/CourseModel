package ru.khrebtov.repositories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khrebtov.entity.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class StudentRepo {
    private static final Logger logger = LoggerFactory.getLogger(StudentRepo.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    public List<Student> findAll() {
        return em.createNamedQuery("findAll", Student.class)
                .getResultList();
    }

    public Student findById(Long id) {
        return em.createNamedQuery("findById", Student.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Long countAll() {
        return em.createNamedQuery("countAll", Long.class)
                .getSingleResult();
    }

    @Transactional
    public void saveOrUpdate(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        }
        em.merge(student);
    }

    @Transactional
    public void deleteById(Long id) {
        em.createNamedQuery("deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }

    public Student findByName(String name) {
        return em.createNamedQuery("findByName", Student.class)
                .setParameter("name", name)
                .getSingleResult();
    }

}
