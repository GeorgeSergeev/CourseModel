package ru.khrebtov.repositories;

import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Student;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

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

    public void signIntoCourse(Course course, Student student) {
    //TODO
        courseRepository.addStudent(course.getId(), student.getId());
    }

    public List<Course> getStudentCourses(Long studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("Передан  не существующий студент (id=null)");
        }
        return em.createNamedQuery("getStudentCourses", Course.class).setParameter("studentId", studentId).getResultList();
    }
}
