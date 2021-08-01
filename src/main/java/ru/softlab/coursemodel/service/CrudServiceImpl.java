package ru.softlab.coursemodel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.softlab.coursemodel.model.BaseEntity;
import ru.softlab.coursemodel.model.converter.EntityDtoConverter;
import ru.softlab.coursemodel.model.dto.BaseDto;
import ru.softlab.coursemodel.repository.BaseEntityRepository;

import javax.persistence.EntityNotFoundException;

@Slf4j
@Service
@Transactional
public abstract class CrudServiceImpl<D extends BaseDto,
        E extends BaseEntity,
        C extends EntityDtoConverter<E, D>,
        R extends BaseEntityRepository<E>>
        implements CrudService<D> {

    protected String entityName = "Entity";

    @Autowired
    protected R repository;

    @Autowired
    protected C converter;

    @Override
    public D create(D dto) {
        E entity = converter.toEntity(dto);
        return converter.toDto(repository.save(entity));
    }

    @Override
    public D findById(Integer id) {
        return converter.toDto(repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, entityName, id))));
    }

    @Override
    public D update(D dto) {
        if (repository.existsById(dto.getId())) {
            E entity = converter.toEntity(dto);
            return converter.toDto(repository.save(entity));
        } else {
            throw new EntityNotFoundException(String.format(ENTITY_NOT_FOUND_MESSAGE, entityName, dto.getId()));
        }
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
