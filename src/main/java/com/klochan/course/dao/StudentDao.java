package com.klochan.course.dao;

import com.klochan.course.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends CrudRepository< Student, Integer >, PagingAndSortingRepository< Student, Integer > {

}
