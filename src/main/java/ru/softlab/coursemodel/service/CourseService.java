package ru.softlab.coursemodel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.softlab.coursemodel.exception.OperationForbiddenException;
import ru.softlab.coursemodel.model.Course;
import ru.softlab.coursemodel.model.converter.CourseConverter;
import ru.softlab.coursemodel.model.dto.CourseDto;
import ru.softlab.coursemodel.model.dto.EnrollInCourseDto;
import ru.softlab.coursemodel.model.dto.StudentDto;
import ru.softlab.coursemodel.repository.CompletingCourseRepository;
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

    @Autowired
    private StudentService studentService;

    @Autowired
    private CompletingCourseRepository completingCourseRepository;

    public CourseService() {
        entityName = Course.class.getSimpleName();
    }

    public void enrollInCourse(EnrollInCourseDto dto) {
        StudentDto studentDto = studentService.findById(dto.getStudentId());
        CourseDto courseDto = findById(dto.getCourseId());
        if (!studentDto.getCourseIds().contains(courseDto.getId())) {
            repository.bindStudentAndCourse(studentDto.getId(), courseDto.getId());
        } else {
            throw new OperationForbiddenException(String.format(
                    "Student with id %s already enrolled in course with id %s", studentDto.getId(), courseDto.getId()));
        }
    }

    public void removeFromCourse(EnrollInCourseDto dto) {
        StudentDto studentDto = studentService.findById(dto.getStudentId());
        CourseDto courseDto = findById(dto.getCourseId());
        Integer studentId = studentDto.getId();
        Integer courseId = courseDto.getId();
        if (studentDto.getCourseIds().contains(courseDto.getId())) {
            completingCourseRepository.deleteAllByStudentIdAndCourseId(studentId, courseId);
            repository.unbindStudentAndCourse(studentId, courseId);
        }
    }
}
