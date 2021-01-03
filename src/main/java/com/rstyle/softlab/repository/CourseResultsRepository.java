package com.rstyle.softlab.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rstyle.softlab.models.Course;
import com.rstyle.softlab.models.CourseResults;
import com.rstyle.softlab.models.Student;
import com.rstyle.softlab.projections.CustomProjection;

@Repository
public interface CourseResultsRepository extends CrudRepository<CourseResults, Long>{
	
	CourseResults findByStudentAndCourse(Student student, Course course);
	
	@Query(value = "SELECT professor.name AS ProfessorName, course.name AS CourseName, COUNT(DISTINCT student_id) AS StudentsAmout, course.success_rate AS SuccessRate\r\n" + 
			"FROM course_results\r\n" + 
			"LEFT JOIN course ON course_results.course_id=course.course_id\r\n" + 
			"LEFT JOIN professor ON course.professor_id=professor.id\r\n" + 
			"GROUP BY course.name\r\n" + 
			"ORDER BY professor.name DESC;", nativeQuery = true)
	List<CustomProjection> getAll();
}
