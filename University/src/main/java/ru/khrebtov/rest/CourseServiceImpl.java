package ru.khrebtov.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.dtoEntity.DtoCourse;
import ru.khrebtov.repositories.CourseRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class CourseServiceImpl implements CourseServiceRest {
    Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @EJB
    private CourseRepository courseRepository;

    @Override
    public List<DtoCourse> findAll() {
        logger.info("All courses");
        List<DtoCourse> list = new ArrayList<>();
        for (Course course : courseRepository.findAll()) {
            course.setStudents(courseRepository.getCourseStudents(course.getId()));
            course.setStudyCourses(courseRepository.getCourseStudy(course.getId()));
            course.setProfessors(courseRepository.getCourseProfessor(course.getId()));
            DtoCourse dtoCourse = new DtoCourse(course);
            list.add(dtoCourse);
        }
        return list;
    }

    @Override
    public DtoCourse findById(Long id) {
        logger.info("find course by id = {}", id);
        Course course = courseRepository.findById(id);
        course.setStudents(courseRepository.getCourseStudents(course.getId()));
        course.setStudyCourses(courseRepository.getCourseStudy(course.getId()));
        course.setProfessors(courseRepository.getCourseProfessor(course.getId()));
        return new DtoCourse(course);
    }

    @Override
    public Long countAll() {
        logger.info("count students");
        return courseRepository.countAll();
    }

    @Override
    public void insert(DtoCourse course) {
        logger.info("Try insert course with id {}", course.getId());
        if (course.getId() != null) {
            logger.error("Был передан существующий курс id!=null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(course);
    }

    @Override
    public void update(DtoCourse course) {
        logger.info("Try insert course with id {}", course.getId());
        if (course.getId() == null) {
            logger.error("Был передан не существующий курс id==null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(course);
    }

    @TransactionAttribute
    public void saveOrUpdate(DtoCourse course) {
        logger.info("Saving student with id {}", course.getId());
        courseRepository.saveOrUpdate(new Course(course));
    }

    @Override
    @TransactionAttribute
    public void deleteById(Long id) {
        logger.info("Deleting course with id {}", id);
        courseRepository.deleteById(id);
    }

    @Override
    @TransactionAttribute
    public boolean addStudentIntoCourse(Long courseId, Long studentId) {
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
