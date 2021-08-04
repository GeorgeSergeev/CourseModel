package ru.softlab.coursemodel.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.softlab.coursemodel.model.Professor;
import ru.softlab.coursemodel.model.converter.ProfessorConverter;
import ru.softlab.coursemodel.model.dto.ProfessorDto;
import ru.softlab.coursemodel.repository.ProfessorRepository;

@Service
@Transactional
public class ProfessorService extends CrudServiceImpl<ProfessorDto,
        Professor,
        ProfessorConverter,
        ProfessorRepository> {

    public ProfessorService() {
        entityName = Professor.class.getSimpleName();
    }
}
