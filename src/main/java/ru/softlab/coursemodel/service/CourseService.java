package ru.softlab.coursemodel.service;

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

import java.util.Collection;

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

    @Override
    public void delete(Integer id) {
        Collection<Integer> studentIds = studentRepository.findAllByCourseId(id);
        studentIds.forEach(studentId -> repository.unbindStudentAndCourse(studentId, id));
        studentIds.forEach(studentId -> completingCourseRepository.deleteAllByStudentIdAndCourseId(studentId, id));
        super.delete(id);
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

    public Collection<CourseDto> findAllFinishedCourses(StudentDto studentDto) {
        Collection<Course> courses = repository.findAllByStudentIdAndFinalMarkIsNotNull(studentDto.getId());
        return converter.toDto(courses);
    }
}
