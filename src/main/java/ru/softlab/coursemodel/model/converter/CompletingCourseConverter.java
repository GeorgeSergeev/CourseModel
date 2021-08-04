package ru.softlab.coursemodel.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.softlab.coursemodel.model.CompletingCourse;
import ru.softlab.coursemodel.model.dto.CompletingCourseDto;
import ru.softlab.coursemodel.model.dto.CourseDto;
import ru.softlab.coursemodel.model.dto.StudentDto;
import ru.softlab.coursemodel.service.CourseService;
import ru.softlab.coursemodel.service.StudentService;

@Component
public class CompletingCourseConverter extends AbstractConverter<CompletingCourse, CompletingCourseDto> {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentConverter studentConverter;

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseConverter courseConverter;

    @Override
    public CompletingCourseDto toDto(CompletingCourse entity) {
        return CompletingCourseDto.builder()
                .id(entity.getId())
                .version(entity.getVersion())
                .studentId(entity.getStudent().getId())
                .courseId(entity.getCourse().getId())
                .mark(entity.getMark())
                .build();
    }

    @Override
    public CompletingCourse toEntity(CompletingCourseDto dto) {
        StudentDto studentDto = studentService.findById(dto.getStudentId());
        CourseDto courseDto = courseService.findById(dto.getCourseId());
        return CompletingCourse.builder()
                .id(dto.getId())
                .version(dto.getVersion())
                .student(studentConverter.toEntity(studentDto))
                .course(courseConverter.toEntity(courseDto))
                .mark(dto.getMark())
                .build();
    }
}
