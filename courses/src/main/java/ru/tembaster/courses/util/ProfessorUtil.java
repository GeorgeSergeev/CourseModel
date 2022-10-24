package ru.tembaster.courses.util;

import ru.tembaster.courses.model.Professor;
import ru.tembaster.courses.to.ProfessorTo;

public class ProfessorUtil {

    public static ProfessorTo CreateTo(Professor pr, Integer studentsCount, Double avgPerformance) {
        return new ProfessorTo(pr.getId(), pr.getName(), pr.getAddress(), pr.getPhone(), pr.getPayment(), studentsCount,
                                avgPerformance);
    }
}
