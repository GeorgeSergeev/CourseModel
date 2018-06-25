package com.klochan.course.dao;

import com.klochan.course.model.Course;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseDao extends CrudRepository< Course, Integer >, PagingAndSortingRepository< Course, Integer > {


}
