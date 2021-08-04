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
        StudentDto studentDto = studentService.findById(dto.getStudentId());
        CourseDto courseDto = courseService.findById(dto.getCourseId());
        if (repository.existsFinalMarkByStudentIdAndCourseId(studentDto.getId(), courseDto.getId())) {
            return super.create(dto);
        } else {
            throw new OperationForbiddenException(
                    String.format("Forbidden add new mark. Student with id '%s' already finished course with id '%s'",
                            studentDto.getId(), courseDto.getId()));
        }
    }

    public float summariseMark(Integer studentId, Integer courseId) {
        StudentDto studentDto = studentService.findById(studentId);
        CourseDto courseDto = courseService.findById(courseId);
        float mark = findAverageMark(studentDto.getId(), courseDto.getId());
        repository.setMarkByStudentIdAndCourseId(studentDto.getId(), courseDto.getId(), mark);
        return mark;
    }

    public float findAverageMark(Integer studentId, Integer courseId) {
        StudentDto studentDto = studentService.findById(studentId);
        CourseDto courseDto = courseService.findById(courseId);
        Collection<Integer> marks =
                repository.findAllMarksByStudentIdAndCourseId(studentDto.getId(), courseDto.getId());
        return findAverageNumber(marks);
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
                .orElseThrow();
    }
}
