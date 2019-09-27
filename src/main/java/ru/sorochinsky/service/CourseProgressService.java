package ru.sorochinsky.service;

import ru.sorochinsky.model.CourseProgress;

import java.util.List;

/**
 * Service class for {@link ru.sorochinsky.model.CourseProgress}
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

public interface CourseProgressService {
    CourseProgress addCourseProgress(CourseProgress courseProgress);
    void delete(Long id);
    CourseProgress getByName(String name);
    CourseProgress editCourseProgress(CourseProgress courseProgress);
    List<CourseProgress> getAll();

//    Дополнительные методы согласно заданию:
//    - получить текущий средний балл (void)
//    - получить финальную отметку (void)
    void avgPoint(List<Integer> points);
    void finalPoint(Integer finalPoint);
}
