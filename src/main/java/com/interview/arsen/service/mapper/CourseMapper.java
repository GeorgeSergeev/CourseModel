package com.interview.arsen.service.mapper;

import com.interview.arsen.domain.*;
import com.interview.arsen.service.dto.CourseDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Course and its DTO CourseDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CourseMapper extends EntityMapper<CourseDTO, Course> {


    @Mapping(target = "subjects", ignore = true)
    @Mapping(target = "students", ignore = true)
    @Mapping(target = "teachers", ignore = true)
    Course toEntity(CourseDTO courseDTO);

    default Course fromId(Long id) {
        if (id == null) {
            return null;
        }
        Course course = new Course();
        course.setId(id);
        return course;
    }
}
