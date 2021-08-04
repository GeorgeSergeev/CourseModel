package ru.softlab.coursemodel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.softlab.coursemodel.model.Student;
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
    private CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CompletingCourseRepository completingCourseRepository;

    public StudentService() {
        entityName = Student.class.getSimpleName();
    }

    @Override
    public StudentDto update(StudentDto dto) {
        Collection<Integer> currentCourseIds = courseRepository.findAllCourseIdsByStudentId(dto.getId());
        currentCourseIds.stream()
                .filter(courseId -> !dto.getCourseIds().contains(courseId))
                .forEach(courseId -> completingCourseRepository.deleteAllByStudentIdAndCourseId(dto.getId(), courseId));
        return super.update(dto);
    }

    @Override
    public void delete(Integer id) {
        Collection<Integer> currentCourseIds = courseRepository.findAllCourseIdsByStudentId(id);
        currentCourseIds.forEach(courseId -> completingCourseRepository.deleteAllByStudentIdAndCourseId(id, courseId));
        super.delete(id);
    }

    public Collection<CourseDto> findAllCompletedCourses(Integer studentId) {
        StudentDto studentDto = findById(studentId);
        return courseService.findAllFinishedCourses(studentDto);
    }
}
