package ru.softlab.coursemodel.model.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.softlab.coursemodel.model.Course;
import ru.softlab.coursemodel.model.Professor;
import ru.softlab.coursemodel.model.dto.CourseDto;
import ru.softlab.coursemodel.model.dto.ProfessorDto;
import ru.softlab.coursemodel.service.CourseService;

import java.util.Collection;
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
                .courseId(entity.getCourse().getId())
                .build();
    }

    @Override
    public Professor toEntity(ProfessorDto dto) {
        CourseDto courseDto = courseService.findById(dto.getCourseId());
        Course course = courseConverter.toEntity(courseDto);
        return Professor.builder()
                .id(dto.getId())
                .version(dto.getVersion())
                .name(dto.getName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .payment(dto.getPayment())
                .course(course)
                .build();
    }
}
