package com.rstyle.softlab.services;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rstyle.softlab.models.Student;
import com.rstyle.softlab.repository.StudentRepository;

@Service
public class StudentService implements DAO<Student>{

	@Autowired
	private StudentRepository repo;
	
	@Autowired 
	private EntityManagerFactory emf;
	
	public List<Student> all(){
		return repo.findAll();
	}
	
	public Student create(Student student) {
		return repo.save(student);
	}
	
	public Student read(Long id) {
		return repo.findById(id).get();
	}

	public void update(Student student) {
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		EntityTransaction tx = null;
		try(Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			session.update(student);
			tx.commit();  
		}catch(Exception ex){     
			if(tx!=null)
				tx.rollback();  
			throw ex;                  
		}
	}
	
	public void delete(Student student) {
		repo.delete(student);
	}
}
