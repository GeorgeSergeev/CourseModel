package ru.softlab.coursemodel.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.softlab.coursemodel.model.Professor;
import ru.softlab.coursemodel.model.converter.ProfessorConverter;
import ru.softlab.coursemodel.model.dto.ProfessorDto;
import ru.softlab.coursemodel.model.dto.ProfessorReportDto;
import ru.softlab.coursemodel.repository.ProfessorRepository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProfessorService extends CrudServiceImpl<ProfessorDto,
        Professor,
        ProfessorConverter,
        ProfessorRepository> {

    private static final int PROFESSOR_NAME_INDEX = 0;
    private static final int STUDENTS_AMOUNT_INDEX = 1;
    private static final int AVERAGE_PERFORMANCE_INDEX = 2;

    public ProfessorService() {
        entityName = Professor.class.getSimpleName();
    }

    public List<ProfessorReportDto> findProfessorReport() {
        List<ProfessorReportDto> result = new ArrayList<>();
        Object[] professorReportsArray = repository.findAllProfessorReports();
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
