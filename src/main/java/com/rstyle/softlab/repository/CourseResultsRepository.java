package com.rstyle.softlab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rstyle.softlab.models.CourseResults;

@Repository
public interface CourseResultsRepository extends CrudRepository<CourseResults, Long>{

}
