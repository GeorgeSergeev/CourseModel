package ru.softlab.coursemodel.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.softlab.coursemodel.model.BaseEntity;
import ru.softlab.coursemodel.model.converter.EntityDtoConverter;
import ru.softlab.coursemodel.model.dto.BaseDto;
import ru.softlab.coursemodel.repository.BaseEntityRepository;

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
        return null;
    }

    @Override
    public D findById(Integer id) {
        return null;
    }

    @Override
    public D update(D dto) {
        return null;
    }

    @Override
    public void logicalDelete(Integer id, boolean deleted, int version) {

    }
}
