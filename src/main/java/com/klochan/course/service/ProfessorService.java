package com.klochan.course.service;

import com.klochan.course.dao.ProfessorDao;
import com.klochan.course.model.Professor;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ProfessorService {

	private final ProfessorDao professorDao;

	@Autowired
	public ProfessorService( ProfessorDao professorDao ) {
		this.professorDao = professorDao;
	}

	@Transactional( readOnly = true )
	public List< Professor > getAllProfessors() {
		return Lists.newArrayList( professorDao.findAll() );
	}

	@Transactional( readOnly = true )
	public List< Professor > getAllProfessors( int start, int limit ) {
		return Lists.newArrayList( professorDao.findAll( PageRequest.of( start, limit ) ) );
	}

	@Transactional( readOnly = true )
	public Professor getProfessorById( int professorId ) {
		return professorDao.findById( professorId ).orElseThrow( EntityNotFoundException::new );
	}

	public int addProfessor( Professor professor ) {
		return professorDao.save( professor ).getProfessorId();
	}

	public void updateProfessor( int professorId, Professor professor ) {
		Professor saved = professorDao.findById( professorId ).orElseThrow( EntityNotFoundException::new );
		saved.setName( professor.getName() );
		saved.setAddress( professor.getAddress() );
		saved.setPhone( professor.getPhone() );
		saved.setSalary( professor.getSalary() );
		professorDao.save( saved );
	}

	public void deleteProfessorById( int professorId ) {
		if ( !professorDao.existsById( professorId ) ) throw new EntityNotFoundException();
		professorDao.delete( getProfessorById( professorId ) );
	}

	public void deleteAllProfessors() {
		professorDao.deleteAll();
	}
}
