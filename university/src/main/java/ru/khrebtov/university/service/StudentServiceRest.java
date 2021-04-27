package ru.khrebtov.university.service;

import ru.khrebtov.university.entity.dtoEntity.DtoCourse;
import ru.khrebtov.university.entity.dtoEntity.DtoStudent;
import ru.khrebtov.university.entity.dtoEntity.DtoStudyCourse;

import java.util.List;
import java.util.Set;

public interface StudentServiceRest {

    List<DtoStudent> findAll();

    DtoStudent findById(Long id);

    Long countAll();

    void insert(DtoStudent student);

    void update(DtoStudent student);

    void deleteById(Long id);

    void signIntoCourse(DtoStudyCourse studyCourse);

     Set<DtoCourse> getStudentCourses(Long studentId);
}
