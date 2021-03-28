package ru.khrebtov.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Student;
import ru.khrebtov.repositories.CourseRepository;
import ru.khrebtov.repositories.StudentRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ws.rs.PathParam;
import java.util.List;

@Stateless
public class CourseServiceImpl implements CourseServiceRest {
    Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @EJB
    private CourseRepository courseRepository;
    @EJB
    private StudentRepository studentRepository;

    @Override
    public List<Course> findAll() {
        logger.info("All courses");
        return courseRepository.findAll();
    }

    @Override
    public Course findById(Long id) {
        logger.info("find course by id = {}", id);
        return courseRepository.findById(id);
    }

    @Override
    public Long countAll() {
        logger.info("count students");
        return courseRepository.countAll();
    }

    @Override
    public void insert(Course course) {
        logger.info("Try insert course with id {}", course.getId());
        if (course.getId() != null) {
            logger.error("Был передан существующий курс id!=null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(course);
    }

    @Override
    public void update(Course course) {
        logger.info("Try insert course with id {}", course.getId());
        if (course.getId() == null) {
            logger.error("Был передан не существующий курс id==null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(course);
    }

    @TransactionAttribute
    public void saveOrUpdate(Course course) {
        logger.info("Saving student with id {}", course.getId());
        courseRepository.saveOrUpdate(course);
    }

    @Override
    @TransactionAttribute
    public void deleteById(Long id) {
        logger.info("Deleting course with id {}", id);
        courseRepository.deleteById(id);
    }

    @Override
    @TransactionAttribute
    public boolean addStudentIntoCourse( Long courseId, Long studentId) {
        logger.info("Adding student into course with course_id {}, student_id{}", courseId, studentId);
        //TODO
       return courseRepository.addStudent(courseId, studentId);
    }

    @Override
    @TransactionAttribute
    public void deleteStudentFromCourse(Long courseId, Long studentId) {
        logger.info("Deleting student from course with course_id {}, student_id{}", courseId, studentId);
        //TODO
        courseRepository.deleteStudent(courseId, studentId);
    }
}
