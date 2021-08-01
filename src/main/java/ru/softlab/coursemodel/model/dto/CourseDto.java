package ru.softlab.coursemodel.model.dto;

import lombok.Getter;
import lombok.Setter;
import ru.softlab.coursemodel.model.dto.validation.scenario.Create;
import ru.softlab.coursemodel.model.dto.validation.scenario.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CourseDto extends BaseDto {

    @NotBlank(groups = {Create.class, Update.class})
    private String name;

    @Size(max = 255, groups = {Create.class, Update.class})
    private Integer number;

    @Positive(groups = {Create.class, Update.class})
    private Float price;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private Integer version;
        private String name;
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

        public Builder name(String name) {
            this.name = name;
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
            courseDto.setName(name);
            courseDto.setNumber(number);
            courseDto.setPrice(price);
            return courseDto;
        }
    }
}
