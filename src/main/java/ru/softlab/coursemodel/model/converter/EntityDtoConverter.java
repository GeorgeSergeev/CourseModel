package ru.softlab.coursemodel.model.converter;

import ru.softlab.coursemodel.model.BaseEntity;
import ru.softlab.coursemodel.model.dto.BaseDto;

import java.util.Collection;

public interface EntityDtoConverter<E extends BaseEntity, D extends BaseDto> {

    D toDto(E entity);

    Collection<D> toDto(Collection<E> entityCollection);

    E toEntity(D dto);

    Collection<E> toEntity(Collection<D> dtoCollection);
}
