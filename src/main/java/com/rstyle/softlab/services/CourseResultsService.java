package com.rstyle.softlab.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rstyle.softlab.models.CourseResults;
import com.rstyle.softlab.repository.CourseResultsRepository;

@Service
public class CourseResultsService {

	@Autowired
	private CourseResultsRepository repo;
	
	@Autowired 
	private EntityManagerFactory emf;
	
	
	public void save(CourseResults entry) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		
		Integer feedback = em.createNativeQuery("INSERT INTO course_results (student_id, course_id) VALUES (:id1, :id2)")
			.setParameter("id1", entry.getStudent().getId())
			.setParameter("id2", entry.getCourse().getCourse_id())
			.executeUpdate();
		
		if(feedback != 1)
			tx.rollback();
		
		tx.commit();
	}
	
	public CourseResults save2(CourseResults enrty) {
		return repo.save(enrty);
	}
	
	public void delete(CourseResults enrty) {
		repo.delete(enrty);
	}
	
	public CourseResults getById(Long id) {
		return repo.findById(id).get();
	}
}
