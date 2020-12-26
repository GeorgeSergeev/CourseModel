package ru.sorochinsky.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sorochinsky.model.CourseProgress;
import ru.sorochinsky.repositiry.CourseProgressRepository;
import ru.sorochinsky.repositiry.CourseRepository;
import ru.sorochinsky.service.CourseProgressService;

import java.util.List;

/**
 * Implementation of {@link ru.sorochinsky.service.CourseProgressService} interface.
 *
 * @author Ivan Sorochinsky
 * @version 1.0
 */

@Service
public class CourceProgressServiceImpl implements CourseProgressService {

    @Autowired
    private CourseProgressRepository courseProgressRepository;

    @Override
    public CourseProgress addCourseProgress(CourseProgress courseProgress) {
        return null;
    }

    @Override
    public void delete(Long id) {
        courseProgressRepository.deleteById(id);
    }

    @Override
    public CourseProgress getByName(String name) {
        return null;
    }

    @Override
    public CourseProgress editCourseProgress(CourseProgress courseProgress) {
        return null;
    }

    @Override
    public List<CourseProgress> getAll() {
        return null;
    }

    @Override
    public void avgPoint(List<Integer> points) {

    }

    @Override
    public void finalPoint(Integer finalPoint) {

    }
}
