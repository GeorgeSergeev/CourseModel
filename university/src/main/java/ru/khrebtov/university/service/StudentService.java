package ru.khrebtov.university.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.khrebtov.university.entity.Course;
import ru.khrebtov.university.entity.Student;
import ru.khrebtov.university.entity.dtoEntity.DtoCourse;
import ru.khrebtov.university.entity.dtoEntity.DtoStudent;
import ru.khrebtov.university.entity.dtoEntity.DtoStudyCourse;
import ru.khrebtov.university.repo.StudentRepository;
import ru.khrebtov.university.repo.StudyCourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentService implements AbstractService<DtoStudent> {
    private final StudentRepository studentRepository;
    private final StudyCourseRepository studyCourseRepository;
    private final StudyCourseService studyCourseService;

    @Autowired
    public StudentService(StudentRepository studentRepository, StudyCourseRepository studyCourseRepository, StudyCourseService studyCourseService) {
        this.studentRepository = studentRepository;
        this.studyCourseRepository = studyCourseRepository;
        this.studyCourseService = studyCourseService;
    }

    @Override
    public List<DtoStudent> findAll() {
        log.info("all students");
        List<DtoStudent> dtoStudents = new ArrayList<>();
        for (Student student : studentRepository.findAll()) {
            DtoStudent dtoStudent = getDtoStudent(student);
            dtoStudents.add(dtoStudent);
        }
        return dtoStudents;
    }

    @Transactional
    public DtoStudent getDtoStudent(Student student) {
        DtoStudent dtoStudent = new DtoStudent(student);
        Long studentId = dtoStudent.getId();
        List<DtoStudyCourse> dtoStudyCourses = new ArrayList<>();
        studentRepository.getStudentStudyCourses(studentId).forEach(studyCourse -> {
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

    @Transactional
    public Float getAverageRatingForAllCourses(List<DtoStudyCourse> studyCourses) {
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
    public DtoStudent findById(Long id) {
        log.info("find student by id = {}", id);
        Student studentById = studentRepository.findById(id);
        return getDtoStudent(studentById);
    }

    @Override
    public Long count() {
        log.info("count students");
        return studentRepository.count();
    }

    @Override
    public void insert(DtoStudent student) {
        log.info("Try insert student with id {}", student.getId());
        if (student.getId() != null) {
            log.error("Был передан существующий студент id!=null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(student);
    }

    @Override
    public void update(DtoStudent student) {
        log.info("Try update student with id {}", student.getId());
        if (student.getId() == null) {
            log.error("Был передан новый студент id=null");
            throw new IllegalArgumentException();
        }
        saveOrUpdate(student);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting student with id {}", id);
        studentRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void saveOrUpdate(DtoStudent student) {
        log.info("Saving student with id {}", student.getId());
        studentRepository.saveOrUpdate(new Student(student.getId(), student.getName(), student.getAddress(),
                student.getPhone(), student.getEmail(), student.getRecordBook(), student.getProgress()));
    }


    public void signIntoCourse(DtoStudyCourse studyCourse) {
        log.info("Adding student into course");
        studyCourseService.insert(studyCourse);
    }


    public List<DtoCourse> getStudentCourses(Long studentId) {
        log.info("Get student Courses for student id {}", studentId);
        List<DtoCourse> courses = new ArrayList<>();
        List<Course> studentCourses = studentRepository.getStudentCourses(studentId);
        studentCourses.forEach(course ->courses.add(new DtoCourse(course)));
        return courses;
    }
}
