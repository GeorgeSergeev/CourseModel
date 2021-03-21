package ru.khrebtov.service;

import ru.khrebtov.entity.DTOentity.CourseRepr;
import ru.khrebtov.repositories.CourseRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class CourseServiceImpl implements CourseService {

    @EJB
    private CourseRepository courseRepository;
    @Override

    @TransactionAttribute
    public List<CourseRepr> getAll() {
        return courseRepository.findAll().stream()
                .map(CourseRepr::new)
                .collect(Collectors.toList());
    }
}
