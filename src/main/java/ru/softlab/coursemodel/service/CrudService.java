package ru.softlab.coursemodel.service;

import ru.softlab.coursemodel.model.dto.BaseDto;

public interface CrudService<D extends BaseDto> {

    D create(D dto);

    D findById(Integer id);

    D update(D dto);

    void logicalDelete(Integer id, boolean deleted, int version);
}
