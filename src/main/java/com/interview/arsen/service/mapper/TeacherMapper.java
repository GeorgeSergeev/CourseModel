package com.interview.arsen.service.mapper;

import com.interview.arsen.domain.*;
import com.interview.arsen.service.dto.TeacherDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Teacher and its DTO TeacherDTO.
 */
@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface TeacherMapper extends EntityMapper<TeacherDTO, Teacher> {



    default Teacher fromId(Long id) {
        if (id == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setId(id);
        return teacher;
    }
}
