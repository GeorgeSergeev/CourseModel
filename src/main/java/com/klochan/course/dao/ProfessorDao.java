package com.klochan.course.dao;

import com.klochan.course.model.Professor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorDao extends CrudRepository< Professor, Integer >, PagingAndSortingRepository< Professor, Integer > {

}
