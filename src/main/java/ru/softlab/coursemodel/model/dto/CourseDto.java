package ru.softlab.coursemodel.model.dto;

import lombok.Getter;
import lombok.Setter;
import ru.softlab.coursemodel.model.dto.validation.scenario.Create;
import ru.softlab.coursemodel.model.dto.validation.scenario.Update;

import javax.validation.constraints.*;

@Getter
@Setter
public class CourseDto extends BaseDto {

    @NotBlank(groups = {Create.class, Update.class})
    @Size(min = 1, max = 255, groups = {Create.class, Update.class})
    private String title;

    @Positive(groups = {Create.class, Update.class})
    @Max(value = Integer.MAX_VALUE, groups = {Create.class, Update.class},
            message = "should be less then " + Integer.MAX_VALUE)
    private Integer number;

    @NotNull
    @Positive(groups = {Create.class, Update.class})
    private Float price;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private Integer version;
        private String title;
        private Integer number;
        private Float price;

        private Builder() {
        }

        public Builder id(Integer id) {
            this.id = id;
            return this;
        }

        public Builder version(Integer version) {
            this.version = version;
            return this;
        }

        public Builder name(String title) {
            this.title = title;
            return this;
        }

        public Builder number(Integer number) {
            this.number = number;
            return this;
        }

        public Builder price(Float price) {
            this.price = price;
            return this;
        }

        public CourseDto build() {
            CourseDto courseDto = new CourseDto();
            courseDto.setId(id);
            courseDto.setVersion(version);
            courseDto.setTitle(title);
            courseDto.setNumber(number);
            courseDto.setPrice(price);
            return courseDto;
        }
    }
}
