package com.interview.arsen.service;

import com.interview.arsen.service.dto.TeacherDTO;
import java.util.List;

/**
 * Service Interface for managing Teacher.
 */
public interface TeacherService {

    /**
     * Save a teacher.
     *
     * @param teacherDTO the entity to save
     * @return the persisted entity
     */
    TeacherDTO save(TeacherDTO teacherDTO);

    /**
     * Get all the teachers.
     *
     * @return the list of entities
     */
    List<TeacherDTO> findAll();

    /**
     * Get the "id" teacher.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TeacherDTO findOne(Long id);

    /**
     * Delete the "id" teacher.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
