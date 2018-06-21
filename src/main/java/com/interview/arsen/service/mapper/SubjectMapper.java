package com.interview.arsen.service.mapper;

import com.interview.arsen.domain.*;
import com.interview.arsen.service.dto.SubjectDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Subject and its DTO SubjectDTO.
 */
@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface SubjectMapper extends EntityMapper<SubjectDTO, Subject> {

    @Mapping(source = "course.id", target = "courseId")
    SubjectDTO toDto(Subject subject);

    @Mapping(source = "courseId", target = "course")
    Subject toEntity(SubjectDTO subjectDTO);

    default Subject fromId(Long id) {
        if (id == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(id);
        return subject;
    }
}
