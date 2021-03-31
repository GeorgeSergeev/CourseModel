package ru.khrebtov.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.khrebtov.entity.Course;
import ru.khrebtov.entity.Student;
import ru.khrebtov.entity.StudyCourse;
import ru.khrebtov.entity.dtoEntity.DtoStudent;
import ru.khrebtov.repositories.StudentRepository;
import ru.khrebtov.repositories.StudyCourseRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateless
public class StudentServiceImpl implements StudentServiceRest {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @EJB
    private StudentRepository studentRepository;
    @EJB
    private StudyCourseRepository studyCourseRepository;

    @Override
    public List<DtoStudent> findAll() {
        logger.info("all students");
        List<DtoStudent> list = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            Long studentId = student.getId();
            Set<StudyCourse> studentStudyCourse = studentRepository.getStudentStudyCourse(studentId);
            studentStudyCourse
                    .forEach(studyCourse -> {
                        studyCourse.setRating(studyCourseRepository.getRatings(studyCourse.getId()));
                        studyCourse.setCourse(studyCourseRepository.getCourseByStudyCourseId(studyCourse.getId()));
                    });
            student.setStudyCourses(studentStudyCourse);
            DtoStudent dtoStudent = new DtoStudent(student);
            list.add(dtoStudent);
        }
        return list;
    }

    @Override
    public DtoStudent findById(Long id) {
        logger.info("find student by id = {}", id);
        Student studentById = studentRepository.findById(id);
        Set<StudyCourse> studentStudyCourse = studentRepository.getStudentStudyCourse(id);
        studentStudyCourse
                .forEach(studyCourse -> {
                    studyCourse.setRating(studyCourseRepository.getRatings(studyCourse.getId()));
                    studyCourse.setCourse(studyCourseRepository.getCourseByStudyCourseId(studyCourse.getId()));
                });
        studentById.setStudyCourses(studentStudyCourse);
        return new DtoStudent(studentById);
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
    public void signIntoCourse(Course course, Student student) {
        //TODO
    }

    @Override
    public Set<Course> getStudentCourses(Long studentId) {
        logger.info("Get student Courses for student id {}", studentId);
        //TODO
        return studentRepository.getStudentCourses(studentId);
    }
}
