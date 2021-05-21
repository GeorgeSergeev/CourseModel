package ru.khrebtov.university.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khrebtov.university.entity.Course;
import ru.khrebtov.university.entity.Student;
import ru.khrebtov.university.entity.StudyCourse;
import ru.khrebtov.university.entity.dtoEntity.DtoCourse;
import ru.khrebtov.university.entity.dtoEntity.DtoProfessor;
import ru.khrebtov.university.entity.dtoEntity.DtoStudent;
import ru.khrebtov.university.entity.dtoEntity.DtoStudyCourse;
import ru.khrebtov.university.repo.CourseRepository;
import ru.khrebtov.university.repo.StudyCourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CourseService implements AbstractService<DtoCourse> {

    private final CourseRepository courseRepository;
    private final StudyCourseRepository studyCourseRepository;
    private final StudyCourseService studyCourseService;

    @Autowired
    public CourseService(CourseRepository courseRepository, StudyCourseRepository studyCourseRepository, StudyCourseService studyCourseService) {
        this.courseRepository = courseRepository;
        this.studyCourseRepository = studyCourseRepository;
        this.studyCourseService = studyCourseService;
    }

    @Override
    public List<DtoCourse> findAll() {
        log.info("All courses");
        List<DtoCourse> list = new ArrayList<>();
        for (Course course : courseRepository.findAll()) {
            DtoCourse dtoCourse = getDtoCourse(course);
            list.add(dtoCourse);
        }
        return list;
    }

    @Transactional
    public DtoCourse getDtoCourse(Course course) {
        DtoCourse dtoCourse = new DtoCourse(course);
        List<DtoProfessor> professors = new ArrayList<>();
        courseRepository.getCourseProfessor(course.getId()).forEach(p -> professors.add(
                new DtoProfessor(p.getId(), p.getName(), p.getAddress(), p.getPhone(), p.getPayment())));
        dtoCourse.setProfessors(professors);

        List<DtoStudent> dtoStudents = new ArrayList<>();
        List<Student> students = courseRepository.getCourseStudents(course.getId());
        students.forEach(s -> {

            StudyCourse studyCourse = studyCourseRepository.findByCourseIdAndStudentId(course.getId(), s.getId());
            List<DtoStudyCourse> dtoStudyCourses = new ArrayList<>();

            DtoStudyCourse dtoStudyCourse = new DtoStudyCourse(studyCourse.getId(),
                    studyCourseRepository.getRatings(studyCourse.getId()));
            dtoStudyCourses.add(dtoStudyCourse);

            dtoStudents.add(new DtoStudent(s, dtoStudyCourses));
        });
        dtoCourse.setStudents(dtoStudents);
        return dtoCourse;
    }

    @Override
    public DtoCourse findById(Long id) {
        log.info("find course by id = {}", id);
        Course course = courseRepository.findById(id);
        return getDtoCourse(course);
    }

    @Override
    public Long count() {
        log.info("count students");
        return courseRepository.count();
    }

    @Override
    public void insert(DtoCourse course) {
        log.info("Try insert course with id {}", course.getId());
        if (course.getId() != null) {
            log.error("Был передан существующий курс id!=null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(course);
    }

    @Override
    public void update(DtoCourse course) {
        log.info("Try insert course with id {}", course.getId());
        if (course.getId() == null) {
            log.error("Был передан не существующий курс id==null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(course);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting course with id {}", id);
        courseRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void saveOrUpdate(DtoCourse course) {
        log.info("Saving student with id {}", course.getId());
        courseRepository.saveOrUpdate(new Course(course));
    }

    @Transactional
    public void addStudentInCourse(DtoStudyCourse studyCourse) {
        log.info("Adding student into course");
        studyCourseService.insert(studyCourse);
    }

    @Transactional
    public void deleteStudentFromCourse(DtoStudyCourse course) {
        log.info("Deleting student from course ");
        StudyCourse studyCourse = studyCourseRepository.findByCourseIdAndStudentId(course.getCourse().getId(),
                course.getStudent().getId());
        studyCourseService.deleteById(studyCourse.getId());
    }
}
