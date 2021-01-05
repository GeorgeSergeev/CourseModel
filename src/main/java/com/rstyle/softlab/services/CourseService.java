package com.rstyle.softlab.services;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rstyle.softlab.models.Course;
import com.rstyle.softlab.repository.CourseRepository;

@Service
public class CourseService implements DAO<Course>{

	@Autowired
	private CourseRepository repo;
	
	@Autowired 
	private EntityManagerFactory emf;
	
	@Override
	public List<Course> all() {
		return repo.findAll();
	}
	
	public Course create(Course enrty) {
		return repo.save(enrty);
	}
	
	public Course read(Long id) {
		return repo.findById(id).get();
	}
	
	public void delete(Course enrty) {
		repo.delete(enrty);
	}
	
	public void update(Course entity) {
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
