package ru.khrebtov.service;

import ru.khrebtov.entity.DTOentity.CourseRepr;

import javax.ejb.Local;
import java.util.List;

@Local
public interface CourseService {
    List<CourseRepr> getAll();
}
