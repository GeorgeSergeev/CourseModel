package ru.softlab.coursemodel.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProfessorReportDto {

    private String professorName;

    private Integer studentsAmount;

    private Float commonAveragePerformance;
}
