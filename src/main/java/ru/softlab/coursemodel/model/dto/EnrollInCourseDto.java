package ru.softlab.coursemodel.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnrollInCourseDto {

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer courseId;
}
