package ru.softlab.coursemodel.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.softlab.coursemodel.model.BaseEntity;
import ru.softlab.coursemodel.model.Course;
import ru.softlab.coursemodel.model.Professor;
import ru.softlab.coursemodel.model.dto.ProfessorDto;
import ru.softlab.coursemodel.service.CourseService;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProfessorConverter extends AbstractConverter<Professor, ProfessorDto> {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseConverter courseConverter;

    @Override
    public ProfessorDto toDto(Professor entity) {
        return ProfessorDto.builder()
                .id(entity.getId())
                .version(entity.getVersion())
                .name(entity.getName())
                .address(entity.getAddress())
                .phone(entity.getPhone())
                .payment(entity.getPayment())
                .courseIds(entity.getCourses().stream()
                        .map(BaseEntity::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public Professor toEntity(ProfessorDto dto) {
        List<Course> courses = null;
        if (dto.getCourseIds() != null) {
            courses = dto.getCourseIds().stream()
                    .map(id -> courseService.findById(id))
                    .map(courseDto -> courseConverter.toEntity(courseDto))
                    .collect(Collectors.toList());
        }
        return Professor.builder()
                .id(dto.getId())
                .version(dto.getVersion())
                .name(dto.getName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .payment(dto.getPayment())
                .courses(courses)
                .build();
    }
}
