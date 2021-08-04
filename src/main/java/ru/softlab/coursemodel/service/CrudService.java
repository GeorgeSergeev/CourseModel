package ru.softlab.coursemodel.service;

import ru.softlab.coursemodel.model.dto.BaseDto;

public interface CrudService<D extends BaseDto> {

    String ENTITY_NOT_FOUND_MESSAGE = "%s with id='%s' not found";
    String VERSION_INVALID_MESSAGE = "%s with id='%s' was already modified";

    D create(D dto);

    D findById(Integer id);

    D update(D dto);

    void delete(Integer id);
}
