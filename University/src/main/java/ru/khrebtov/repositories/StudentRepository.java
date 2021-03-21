package ru.khrebtov.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.DTOentity.StudentRepr;
import ru.khrebtov.entity.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class StudentRepository {
    private static final Logger logger = LoggerFactory.getLogger(StudentRepository.class);

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

//    public void saveOrUpdate(Student student) {
//        if (student.getId() == null) {
//            em.persist(student);
//        }
//        em.merge(student);
//    }

    public Student merge(Student student) {
        return em.merge(student);
    }

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

    public List<Course> getStudentCourses(StudentRepr student) {
        if (student.getId() == null) {
            throw new IllegalArgumentException();
        }
        return em.createNamedQuery("courseLeftJoin", Course.class)
                .setParameter("studentId", student.getId()).getResultList();
    }
}
