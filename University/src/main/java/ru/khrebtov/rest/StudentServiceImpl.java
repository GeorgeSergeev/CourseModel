package ru.khrebtov.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Student;
import ru.khrebtov.entity.dtoEntity.DtoCourse;
import ru.khrebtov.entity.dtoEntity.DtoStudent;
import ru.khrebtov.entity.dtoEntity.DtoStudyCourse;
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
public class StudentServiceImpl implements StudentServiceRest {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @EJB
    private StudentRepository studentRepository;
    @EJB
    private StudyCourseRepository studyCourseRepository;
    @EJB
    private StudyCourseServiceRest studyCourseServiceRest;

    @Override
    public List<DtoStudent> findAll() {
        logger.info("all students");
        List<DtoStudent> dtoStudents = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            DtoStudent dtoStudent = getDtoStudent(student);
            dtoStudents.add(dtoStudent);
        }
        return dtoStudents;
    }

    @Override
    public DtoStudent findById(Long id) {
        logger.info("find student by id = {}", id);
        Student studentById = studentRepository.findById(id);
        return getDtoStudent(studentById);
    }

    private DtoStudent getDtoStudent(Student student) {
        DtoStudent dtoStudent = new DtoStudent(student);
        Long studentId = dtoStudent.getId();
        Set<DtoStudyCourse> dtoStudyCourses = new HashSet<>();
        studentRepository.getStudentStudyCourse(studentId).forEach(studyCourse -> {
            studyCourse.setRating(studyCourseRepository.getRatings(studyCourse.getId()));
            studyCourse.setCourse(studyCourseRepository.getCourseByStudyCourseId(studyCourse.getId()));

            Course course = studyCourse.getCourse();
            dtoStudyCourses.add(new DtoStudyCourse(studyCourse.getId(), studyCourse.getRating(),
                    new DtoCourse(course.getId(), course.getName(), course.getNumber(), course.getCost())));
        });
        dtoStudent.setStudyCourses(dtoStudyCourses);
        dtoStudent.setProgress(getAverageRatingForAllCourses(dtoStudyCourses));
        return dtoStudent;
    }

    private Float getAverageRatingForAllCourses(Set<DtoStudyCourse> studyCourses) {
        float sumAverageRating = 0F;
        int countStudyCoursesWithRatings = 0;
        for (DtoStudyCourse s : studyCourses) {
            Double averageRating = studyCourseRepository.getAverageRating(s.getId());
            if (averageRating != null) {
                countStudyCoursesWithRatings++;
                sumAverageRating += averageRating;
            }
        }
        return (float) Math.round(100 * (sumAverageRating / countStudyCoursesWithRatings)) / 100;
    }

    @Override
    public Long countAll() {
        logger.info("count students");
        return studentRepository.countAll();
    }

    @Override
    public void insert(DtoStudent student) {
        logger.info("Try insert student with id {}", student.getId());
        if (student.getId() != null) {
            logger.error("Был передан существующий студент id!=null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(student);
    }

    @Override
    public void update(DtoStudent student) {
        logger.info("Try update student with id {}", student.getId());
        if (student.getId() == null) {
            logger.error("Был передан новый студент id=null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(student);
    }

    @TransactionAttribute
    public void saveOrUpdate(DtoStudent student) {
        logger.info("Saving student with id {}", student.getId());
        studentRepository.saveOrUpdate(new Student(student));
    }

    @Override
    public void deleteById(Long id) {
        logger.info("Deleting student with id {}", id);
        studentRepository.deleteById(id);
    }

    @Override
    public void signIntoCourse(DtoStudyCourse studyCourse) {
        logger.info("Adding student into course");
        studyCourseServiceRest.insert(studyCourse);
    }

    @Override
    public Set<DtoCourse> getStudentCourses(Long studentId) {
        logger.info("Get student Courses for student id {}", studentId);
        Set<DtoCourse> courses = new HashSet<>();
        Set<Course> studentCourses = studentRepository.getStudentCourses(studentId);
        studentCourses.forEach(course ->courses.add(new DtoCourse(course)));
        return courses;
    }
}
