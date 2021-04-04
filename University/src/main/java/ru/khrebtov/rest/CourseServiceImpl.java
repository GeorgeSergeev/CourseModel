package ru.khrebtov.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Student;
import ru.khrebtov.entity.StudyCourse;
import ru.khrebtov.entity.dtoEntity.DtoCourse;
import ru.khrebtov.entity.dtoEntity.DtoProfessor;
import ru.khrebtov.entity.dtoEntity.DtoStudent;
import ru.khrebtov.entity.dtoEntity.DtoStudyCourse;
import ru.khrebtov.repositories.CourseRepository;
import ru.khrebtov.repositories.StudentRepository;
import ru.khrebtov.repositories.StudyCourseRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Stateless
public class CourseServiceImpl implements CourseServiceRest {
    Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

    @EJB
    private CourseRepository courseRepository;
    @EJB
    private StudyCourseRepository studyCourseRepository;
    @EJB
    private StudentRepository studentRepository;
    @EJB
    private StudyCourseServiceRest studyCourseServiceRest;

    @Override
    public List<DtoCourse> findAll() {
        logger.info("All courses");
        List<DtoCourse> list = new ArrayList<>();
        for (Course course : courseRepository.findAll()) {
            DtoCourse dtoCourse = getDtoCourse(course);
            list.add(dtoCourse);
        }
        return list;
    }

    @Override
    public DtoCourse findById(Long id) {
        logger.info("find course by id = {}", id);
        Course course = courseRepository.findById(id);
        return getDtoCourse(course);
    }

    @TransactionAttribute
    private DtoCourse getDtoCourse(Course course) {
        DtoCourse dtoCourse = new DtoCourse(course);
        Set<DtoProfessor> professors = new HashSet<>();
        courseRepository.getCourseProfessor(course.getId()).forEach(p -> professors.add(
                new DtoProfessor(p.getId(), p.getName(), p.getAddress(), p.getPhone(), p.getPayment())));
        dtoCourse.setProfessors(professors);

        Set<DtoStudent> dtoStudents = new HashSet<>();
        Set<Student> students = courseRepository.getCourseStudents(course.getId());
        students.forEach(s -> {

            StudyCourse studyCourse = studyCourseRepository.findByCourseIdAndStudentId(course.getId(), s.getId());
            Set<DtoStudyCourse> dtoStudyCourses = new HashSet<>();

            DtoStudyCourse dtoStudyCourse = new DtoStudyCourse(studyCourse.getId(),
                    studyCourseRepository.getRatings(studyCourse.getId()));
            dtoStudyCourses.add(dtoStudyCourse);

            dtoStudents.add(new DtoStudent(s, dtoStudyCourses));
        });
        dtoCourse.setStudents(dtoStudents);
        return dtoCourse;
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
    public void addStudentInCourse(DtoStudyCourse studyCourse) {
        logger.info("Adding student into course");
        studyCourseServiceRest.insert(studyCourse);
    }

    @Override
    @TransactionAttribute
    public void deleteStudentFromCourse(DtoStudyCourse course) {
        logger.info("Deleting student from course ");
        StudyCourse studyCourse = studyCourseRepository.findByCourseIdAndStudentId(course.getCourse().getId(),
                course.getStudent().getId());
        studyCourseServiceRest.deleteById(studyCourse.getId());
    }
}
