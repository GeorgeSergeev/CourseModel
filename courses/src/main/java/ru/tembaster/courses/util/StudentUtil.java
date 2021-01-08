package ru.tembaster.courses.util;

import ru.tembaster.courses.model.Course;
import ru.tembaster.courses.model.CourseProgress;
import ru.tembaster.courses.model.Student;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Set;

public class StudentUtil {

    public static Double getAvgPerformanceByStudents(Set<Student> students) {
        List<Integer> marksList = new ArrayList<>();
        for (Student st : students) {
            Set<CourseProgress> courses = st.getCourses();
            for (CourseProgress cp : courses) {
                marksList.add(cp.getMark());
            }
        }
        IntSummaryStatistics stat = marksList.stream().mapToInt((x) -> x).summaryStatistics();
        return stat.getAverage();
    }



    public static Double getAvgPerformanceByCourses(Set<Course> courses) {
        List<Integer> marksList = new ArrayList<>();
        for (Course c : courses) {
            Set<CourseProgress> studs = c.getStudents();
            for (CourseProgress cp : studs) {
                marksList.add(cp.getMark());
            }
        }
        IntSummaryStatistics stat = marksList.stream().mapToInt((x) -> x).summaryStatistics();
        return stat.getAverage();
    }


}
