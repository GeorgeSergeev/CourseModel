package ru.softlab.coursemodel.model.converter;

import org.springframework.stereotype.Component;
import ru.softlab.coursemodel.model.BaseEntity;
import ru.softlab.coursemodel.model.dto.BaseDto;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public abstract class AbstractConverter<E extends BaseEntity, D extends BaseDto> implements EntityDtoConverter<E, D> {

    @Override
    public Collection<D> toDto(Collection<E> entityCollection) {
        return entityCollection.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Collection<E> toEntity(Collection<D> dtoCollection) {
        return dtoCollection.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
