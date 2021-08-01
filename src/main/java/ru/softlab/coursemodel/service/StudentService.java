package ru.softlab.coursemodel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.softlab.coursemodel.model.Course;
import ru.softlab.coursemodel.model.Student;
import ru.softlab.coursemodel.model.converter.CourseConverter;
import ru.softlab.coursemodel.model.converter.StudentConverter;
import ru.softlab.coursemodel.model.dto.CourseDto;
import ru.softlab.coursemodel.model.dto.StudentDto;
import ru.softlab.coursemodel.repository.CompletingCourseRepository;
import ru.softlab.coursemodel.repository.CourseRepository;
import ru.softlab.coursemodel.repository.StudentRepository;

import java.util.Collection;

@Slf4j
@Service
@Transactional
public class StudentService extends CrudServiceImpl<StudentDto,
        Student,
        StudentConverter,
        StudentRepository> {

    @Autowired
    private CourseConverter courseConverter;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CompletingCourseRepository completingCourseRepository;

    public StudentService() {
        entityName = Student.class.getSimpleName();
    }

    public Collection<CourseDto> findAllCompletedCourses(Integer studentId) {
        Collection<Course> courses = courseRepository.findAllByStudentIdAndFinalMarkIsNotNull(studentId);
        return courseConverter.toDto(courses);
    }

    public float findAverageMarkByCourse(Student student, Course course) {
        Collection<Integer> marks = completingCourseRepository.findAllMarksByStudentAndCourse(student, course);
        return findAverageNumber(marks);
    }

    public void recountAveragePerformance(Student student) {
        Collection<Integer> marks = completingCourseRepository.findAllMarksByStudent(student);
        float mark = findAverageNumber(marks);
        student.setAveragePerformance(mark);
        repository.save(student);
    }

    private float findAverageNumber(Collection<Integer> numbers) {
        return (float) numbers.stream()
                .mapToInt(m -> m)
                .average()
                .orElseThrow();
    }
}
