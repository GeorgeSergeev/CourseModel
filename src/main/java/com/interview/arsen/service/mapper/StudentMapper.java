package com.interview.arsen.service.mapper;

import com.interview.arsen.domain.*;
import com.interview.arsen.service.dto.StudentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Student and its DTO StudentDTO.
 */
@Mapper(componentModel = "spring", uses = {CourseMapper.class})
public interface StudentMapper extends EntityMapper<StudentDTO, Student> {



    default Student fromId(Long id) {
        if (id == null) {
            return null;
        }
        Student student = new Student();
        student.setId(id);
        return student;
    }
}
