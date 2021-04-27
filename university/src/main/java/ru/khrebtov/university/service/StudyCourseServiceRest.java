package ru.khrebtov.university.service;


import ru.khrebtov.university.entity.dtoEntity.DtoStudyCourse;

import java.util.List;

public interface StudyCourseServiceRest {

    List<DtoStudyCourse> findAll();

    DtoStudyCourse findById(Long id);

    Long countAll();

    void insert(DtoStudyCourse studyCourse);

    void update(DtoStudyCourse studyCourse);

    void deleteById(Long id);
}
