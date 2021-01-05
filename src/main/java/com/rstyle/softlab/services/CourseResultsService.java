package com.rstyle.softlab.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rstyle.softlab.models.Course;
import com.rstyle.softlab.models.CourseResults;
import com.rstyle.softlab.models.Student;
import com.rstyle.softlab.projections.CustomProjection;
import com.rstyle.softlab.repository.CourseResultsRepository;

@Service
public class CourseResultsService implements DAO<CourseResults>{

	@Autowired
	private CourseResultsRepository repo;
	
	@Autowired 
	private EntityManagerFactory emf;
	
	public List<CourseResults> all() {
		return repo.findAll();
	}
	
	public void save(CourseResults entry) {
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();        
		   
			em.createNativeQuery("INSERT INTO course_results (student_id, course_id) VALUES (:id1, :id2)")
			   .setParameter("id1", entry.getStudent().getId())
			   .setParameter("id2", entry.getCourse().getCourse_id())
			   .executeUpdate();
		   
			tx.commit();  
		}catch(Exception ex){     
			if(tx!=null)
				tx.rollback();  
			throw ex;                  
		}finally {
			if(em!=null)
				em.close();
		}
	}
	
	public void delete(CourseResults enrty) {
		repo.delete(enrty);
	}
	
	public CourseResults read(Long id) {
		return repo.findById(id).get();
	}
	
	public CourseResults findByStudentAndCourse(Student student, Course course) {
		return repo.findByStudentAndCourse(student, course);
	}
	
	public List<CustomProjection> successRate() {
		return repo.getSuccessRate();
	}

	@Override
	public CourseResults create(CourseResults entity) {
		return null;
	}

	public void update(CourseResults entity) {
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		EntityTransaction tx = null;
		try(Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			session.update(entity);
			tx.commit();  
		}catch(Exception ex){     
			if(tx!=null)
				tx.rollback();  
			throw ex;                  
		}
	}
}
