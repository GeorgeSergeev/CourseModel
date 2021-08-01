package ru.softlab.coursemodel.model.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.softlab.coursemodel.model.Course;
import ru.softlab.coursemodel.model.dto.CourseDto;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CourseConverter implements EntityDtoConverter<Course, CourseDto> {

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
    public Collection<CourseDto> toDto(Collection<Course> entities) {
        return entities.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Course toEntity(CourseDto dto) {
        return Course.builder()
                .id(dto.getId())
                .name(dto.getTitle())
                .number(dto.getNumber())
                .price(dto.getPrice())
                .build();
    }

    @Override
    public Collection<Course> toEntity(Collection<CourseDto> dtoCollection) {
        return dtoCollection.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
