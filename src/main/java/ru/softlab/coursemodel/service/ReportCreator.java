package ru.softlab.coursemodel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.softlab.coursemodel.model.dto.ProfessorReportDto;
import ru.softlab.coursemodel.repository.ProfessorRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

@Service
public class ReportCreator {

    private static final int PROFESSOR_NAME_INDEX = 0;
    private static final int STUDENTS_AMOUNT_INDEX = 1;
    private static final int AVERAGE_PERFORMANCE_INDEX = 2;

    @Autowired
    private ProfessorRepository professorRepository;

    public Collection<ProfessorReportDto> findProfessorReports() {
        Collection<ProfessorReportDto> result = new ArrayList<>();
        Object[] professorReportsArray = professorRepository.findAllProfessorReports();
        for (Object professorReport : professorReportsArray) {
            Object[] fields = (Object[]) professorReport;
            ProfessorReportDto professorReportDto = ProfessorReportDto.builder()
                    .professorName((String) fields[PROFESSOR_NAME_INDEX])
                    .studentsAmount(((BigInteger) fields[STUDENTS_AMOUNT_INDEX]).intValue())
                    .commonAveragePerformance(((BigDecimal) fields[AVERAGE_PERFORMANCE_INDEX]).floatValue())
                    .build();
            result.add(professorReportDto);
        }
        return result;
    }
}
