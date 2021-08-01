package ru.softlab.coursemodel.model.dto;

import lombok.Getter;
import lombok.Setter;
import ru.softlab.coursemodel.model.dto.validation.scenario.Create;
import ru.softlab.coursemodel.model.dto.validation.scenario.Update;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Getter
@Setter
public class BaseDto {

    @NotNull(groups = Update.class)
    @Null(groups = Create.class)
    private Integer id;

    @NotNull(groups = Update.class)
    @Null(groups = Create.class)
    private Integer version;
}
