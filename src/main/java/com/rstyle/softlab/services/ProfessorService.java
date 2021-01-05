package com.rstyle.softlab.services;

import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rstyle.softlab.models.Professor;
import com.rstyle.softlab.repository.ProfessorRepository;

@Service
public class ProfessorService implements DAO<Professor>{

	@Autowired
	private ProfessorRepository repo;
	
	@Autowired 
	private EntityManagerFactory emf;
	
	public List<Professor> all(){
		return repo.findAll();
	}
	
	public Professor create(Professor professor) {
		return repo.save(professor);
	}
	
	public Professor read(Long id) {
		return repo.findById(id).get();
	}
	
	public void update(Professor professor) {
		SessionFactory sessionFactory = emf.unwrap(SessionFactory.class);
		EntityTransaction tx = null;
		try(Session session = sessionFactory.openSession()) {
			tx = session.beginTransaction();
			session.update(professor);
			tx.commit();  
		}catch(Exception ex){     
			if(tx!=null)
				tx.rollback();  
			throw ex;                  
		}
	}
	
	public void delete(Professor professor) {
		repo.delete(professor);
	}
	
//	public void update2(Professor professor) {
//		EntityManager em = emf.createEntityManager();
//		EntityTransaction tx = null;
//		try {
//			tx = em.getTransaction();
//			tx.begin();   
//		
//			em.createNativeQuery("UPDATE professor SET name=:name, address=:addr, phone=:phone, payment=:payment WHERE id=:id")
//				.setParameter("id", professor.getId())
//				.setParameter("name", professor.getName())
//				.setParameter("addr", professor.getAddress())
//				.setParameter("phone", professor.getPhone())
//				.setParameter("payment", professor.getPayment())
//				.executeUpdate();
//			
//			tx.commit();  
//		}catch(Exception ex){     
//			if(tx!=null)
//				tx.rollback();  
//			throw ex;                  
//		}finally {
//			if(em!=null)
//				em.close();
//		}
//	}
	
}
