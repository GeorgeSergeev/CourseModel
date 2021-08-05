package ru.softlab.coursemodel.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
public class EnrollInCourseDto {

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer courseId;
}
