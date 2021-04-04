package ru.khrebtov.repositories;

import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Student;
import ru.khrebtov.entity.StudyCourse;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class StudentRepository {

    @PersistenceContext(unitName = "ds")
    private EntityManager em;
    @EJB
    private CourseRepository courseRepository;

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

    public void saveOrUpdate(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        }
        em.merge(student);
    }

    public void deleteById(Long id) {
        em.createNamedQuery("deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }


    public Set<Course> getStudentCourses(Long studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("Передан  не существующий студент (id=null)");
        }
        return new HashSet<>(em.createNamedQuery("getStudentCourses", Course.class)
                .setParameter("studentId", studentId).getResultList());
    }

    public Set<StudyCourse> getStudentStudyCourse(Long studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("Передан  не существующий студент (id=null)");
        }
        return new HashSet<>(em.createNamedQuery("getStudentStudyCourse", StudyCourse.class)
                .setParameter("studentId", studentId).getResultList());
    }
}
