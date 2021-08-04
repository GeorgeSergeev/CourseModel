package ru.softlab.coursemodel.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.softlab.coursemodel.model.BaseEntity;
import ru.softlab.coursemodel.model.Course;
import ru.softlab.coursemodel.model.Student;
import ru.softlab.coursemodel.model.dto.StudentDto;
import ru.softlab.coursemodel.service.CourseService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentConverter extends AbstractConverter<Student, StudentDto> {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseConverter courseConverter;

    @Override
    public StudentDto toDto(Student entity) {
        return StudentDto.builder()
                .id(entity.getId())
                .version(entity.getVersion())
                .name(entity.getName())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .recordBookNumber(entity.getRecordBookNumber())
                .averagePerformance(entity.getAveragePerformance())
                .courseIds(entity.getCourses().stream()
                        .map(BaseEntity::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Student toEntity(StudentDto dto) {
        List<Course> courses = null;
        if (dto.getCourseIds() != null) {
            courses = dto.getCourseIds().stream()
                    .map(id -> courseService.findById(id))
                    .map(courseDto -> courseConverter.toEntity(courseDto))
                    .collect(Collectors.toList());
        }
        return Student.builder()
                .id(dto.getId())
                .version(dto.getVersion())
                .name(dto.getName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .recordBookNumber(dto.getRecordBookNumber())
                .averagePerformance(dto.getAveragePerformance())
                .courses(courses)
                .build();
    }
}
