package ru.softlab.coursemodel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.softlab.coursemodel.exception.OperationForbiddenException;
import ru.softlab.coursemodel.model.CompletingCourse;
import ru.softlab.coursemodel.model.converter.CompletingCourseConverter;
import ru.softlab.coursemodel.model.dto.CompletingCourseDto;
import ru.softlab.coursemodel.model.dto.CourseDto;
import ru.softlab.coursemodel.model.dto.StudentDto;
import ru.softlab.coursemodel.repository.CompletingCourseRepository;

import java.util.Collection;

@Service
@Transactional
public class CompletingCourseService extends CrudServiceImpl<CompletingCourseDto,
        CompletingCourse,
        CompletingCourseConverter,
        CompletingCourseRepository> {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CourseService courseService;

    public CompletingCourseService() {
        entityName = CompletingCourse.class.getSimpleName();
    }

    @Override
    public CompletingCourseDto create(CompletingCourseDto dto) {
        CompletingCourse completingCourse = converter.toEntity(dto);
        Integer studentId = completingCourse.getStudent().getId();
        Integer courseId = completingCourse.getCourse().getId();
        if (!repository.existsFinalMarkByStudentIdAndCourseId(studentId, courseId)) {
            return super.create(dto);
        } else {
            throw new OperationForbiddenException(
                    String.format("Forbidden add new mark. Student with id '%s' already finished course with id '%s'",
                            studentId, courseId));
        }
    }

    @Override
    public CompletingCourseDto update(CompletingCourseDto dto) {
        CompletingCourse completingCourse = converter.toEntity(dto);
        Integer studentId = completingCourse.getStudent().getId();
        Integer courseId = completingCourse.getCourse().getId();
        if (!repository.existsFinalMarkByStudentIdAndCourseId(studentId, courseId)) {
            return super.update(dto);
        } else {
            throw new OperationForbiddenException(
                    String.format("Forbidden update mark. Student with id '%s' already finished course with id '%s'",
                            studentId, courseId));
        }
    }

    @Override
    public void delete(Integer id) {
        CompletingCourseDto completingCourseDto = findById(id);
        CompletingCourse completingCourse = converter.toEntity(completingCourseDto);
        Integer studentId = completingCourse.getStudent().getId();
        Integer courseId = completingCourse.getCourse().getId();
        if (!repository.existsFinalMarkByStudentIdAndCourseId(studentId, courseId)) {
            super.delete(id);
        } else {
            throw new OperationForbiddenException(
                    String.format("Forbidden delete mark. Student with id '%s' already finished course with id '%s'",
                            studentId, courseId));
        }
    }

    public float summariseMark(Integer studentId, Integer courseId) {
        float finalMark = findAverageMark(studentId, courseId);
        if (repository.existsNotSetFinalMarkByStudentIdAndCourseId(studentId, courseId)) {
            repository.setFinalMarkByStudentIdAndCourseId(studentId, courseId, finalMark);
        }
        return finalMark;
    }

    public float findAverageMark(Integer studentId, Integer courseId) {
        StudentDto studentDto = studentService.findById(studentId);
        CourseDto courseDto = courseService.findById(courseId);
        if (studentDto.getCourseIds().contains(courseDto.getId())) {
            Collection<Integer> marks =
                    repository.findAllMarksByStudentIdAndCourseId(studentDto.getId(), courseDto.getId());
            return findAverageNumber(marks);
        } else {
            throw new OperationForbiddenException(String.format(
                    "Student with id '%d' is not enrolled int course with id '%s'", studentId, courseId));
        }
    }

    public void recountAveragePerformance(Integer studentId) {
        StudentDto studentDto = studentService.findById(studentId);
        Collection<Integer> marks = repository.findAllMarksByStudentId(studentDto.getId());
        float mark = findAverageNumber(marks);
        studentDto.setAveragePerformance(mark);
        studentService.update(studentDto);
    }

    private float findAverageNumber(Collection<Integer> numbers) {
        return (float) numbers.stream()
                .mapToInt(m -> m)
                .average()
                .orElse(0f);
    }
}
