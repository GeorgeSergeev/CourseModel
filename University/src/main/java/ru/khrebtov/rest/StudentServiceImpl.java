package ru.khrebtov.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Student;
import ru.khrebtov.repositories.StudentRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;

@Stateless
public class StudentServiceImpl implements StudentServiceRest {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @EJB
    private StudentRepository studentRepository;

    @Override
    public List<Student> findAll() {
        logger.info("all students");
        return studentRepository.findAll();
    }

    @Override
    public Student findById(Long id) {
        logger.info("find student by id = {}",id);
        return studentRepository.findById(id);
    }

    @Override
    public Long countAll() {
        logger.info("count students");
        return studentRepository.countAll();
    }

    @Override
    public void insert(Student student) {
        logger.info("Try insert student with id {}", student.getId());
        if (student.getId() != null) {
            logger.error("Был передан существующий студент id!=null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(student);
    }

    @Override
    public void update(Student student) {
        logger.info("Try update student with id {}", student.getId());
        if (student.getId() == null) {
            logger.error("Был передан новый студент id=null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(student);
    }

    @TransactionAttribute
    public void saveOrUpdate(Student student) {
        logger.info("Saving student with id {}", student.getId());
        studentRepository.saveOrUpdate(student);
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting student with id {}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public void signIntoCourse(Course course, Student student) {
    //TODO
    }

    @Override
    public List<Course> getStudentCourses(Long studentId) {
        logger.info("Get student Courses for student id {}", studentId);
        //TODO
        return studentRepository.getStudentCourses(studentId);
    }
}
