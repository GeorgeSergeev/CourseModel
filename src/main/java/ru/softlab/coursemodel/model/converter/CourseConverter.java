package ru.softlab.coursemodel.model.converter;

import org.springframework.stereotype.Component;
import ru.softlab.coursemodel.model.Course;
import ru.softlab.coursemodel.model.dto.CourseDto;

@Component
public class CourseConverter extends AbstractConverter<Course, CourseDto> {

    @Override
    public CourseDto toDto(Course entity) {
        return CourseDto.builder()
                .id(entity.getId())
                .version(entity.getVersion())
                .name(entity.getTitle())
                .number(entity.getNumber())
                .price(entity.getPrice())
                .build();
    }

    @Override
    public Course toEntity(CourseDto dto) {
        return Course.builder()
                .id(dto.getId())
                .version(dto.getVersion())
                .name(dto.getTitle())
                .number(dto.getNumber())
                .price(dto.getPrice())
                .build();
    }
}
