package ru.khrebtov.university.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khrebtov.university.entity.Course;
import ru.khrebtov.university.entity.Student;
import ru.khrebtov.university.entity.dtoEntity.DtoCourse;
import ru.khrebtov.university.entity.dtoEntity.DtoStudent;
import ru.khrebtov.university.entity.dtoEntity.DtoStudyCourse;
import ru.khrebtov.university.entity.repository.StudentRepository;
import ru.khrebtov.university.entity.repository.StudyCourseRepository;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements StudentServiceRest {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private final StudentRepository studentRepository;
    private final StudyCourseRepository studyCourseRepository;
    private final StudyCourseServiceRest studyCourseServiceRest;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, StudyCourseRepository studyCourseRepository, StudyCourseServiceRest studyCourseServiceRest) {
        this.studentRepository = studentRepository;
        this.studyCourseRepository = studyCourseRepository;
        this.studyCourseServiceRest = studyCourseServiceRest;
    }

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

    @Transactional
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
