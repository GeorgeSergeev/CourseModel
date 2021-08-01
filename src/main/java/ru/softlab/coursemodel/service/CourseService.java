package ru.softlab.coursemodel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.softlab.coursemodel.model.Course;
import ru.softlab.coursemodel.model.Student;
import ru.softlab.coursemodel.model.converter.CourseConverter;
import ru.softlab.coursemodel.model.dto.CourseDto;
import ru.softlab.coursemodel.repository.CourseRepository;
import ru.softlab.coursemodel.repository.StudentRepository;

@Slf4j
@Service
@Transactional
public class CourseService extends CrudServiceImpl<CourseDto,
        Course,
        CourseConverter,
        CourseRepository> {

    @Autowired
    private StudentRepository studentRepository;

    void signUpStudentInCourse(Student student, Course course) {
        studentRepository.bindStudentAndCourse(student.getId(), course.getId(), );
    }
}
