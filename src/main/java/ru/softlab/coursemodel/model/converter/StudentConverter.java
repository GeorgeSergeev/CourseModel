package ru.softlab.coursemodel.model.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.softlab.coursemodel.model.BaseEntity;
import ru.softlab.coursemodel.model.Student;
import ru.softlab.coursemodel.model.dto.StudentDto;
import ru.softlab.coursemodel.service.CourseService;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Component
public class StudentConverter implements EntityDtoConverter<Student, StudentDto> {

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
                .avgPerformance(entity.getAveragePerformance())
                .courseIds(entity.getCourses().stream()
                        .map(BaseEntity::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Collection<StudentDto> toDto(Collection<Student> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Student toEntity(StudentDto dto) {
        return Student.builder()
                .id(dto.getId())
                .version(dto.getVersion())
                .name(dto.getName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .recordBookNumber(dto.getRecordBookNumber())
                .avgPerformance(dto.getAvgPerformance())
                .courses(dto.getCourseIds() != null ? dto.getCourseIds().stream()
                        .map(id -> courseService.findById(id))
                        .map(courseDto -> courseConverter.toEntity(courseDto))
                        .collect(Collectors.toList()) : null)
                .build();
    }

    @Override
    public Collection<Student> toEntity(Collection<StudentDto> dtoCollection) {
        return dtoCollection.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
