package ru.khrebtov.repositories;

import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Student;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Stateless
public class CourseRepository {

    @EJB
    private StudentRepository studentRepository;

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

    public boolean addStudent(Long courseId, Long studentId) {
        if (courseId == null || studentId == null) {
            throw new IllegalArgumentException("Передан не существующий курс (id=null) или не существующий " +
                    "студент (id=null)");
        }
        em.createNativeQuery("insert into course_students (course_id, student_id) VALUES (course_id=:courseId, student_id=:studentId)")
                .setParameter("courseId", courseId)
                .setParameter("studentId", studentId).executeUpdate();
        return true;
    }

    public void deleteStudent(Long courseId, Long studentId) {
        if (courseId == null || studentId == null) {
            throw new IllegalArgumentException("Передан не существующий курс (id=null) или не существующий " +
                    "студент (id=null)");
        }
        em.createNamedQuery("deleteStudentFromCourse")
                .setParameter("courseId", courseId)
                .setParameter("studentId", studentId)
                .executeUpdate();
    }
}
