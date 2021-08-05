package ru.softlab.coursemodel.model.dto;

import lombok.Getter;
import lombok.Setter;
import ru.softlab.coursemodel.model.dto.validation.scenario.Create;
import ru.softlab.coursemodel.model.dto.validation.scenario.Update;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Collection;

@Getter
@Setter
public class ProfessorDto extends BaseDto {

    @NotBlank(groups = {Create.class, Update.class})
    @Size(min = 2, max = 255, groups = {Create.class, Update.class})
    private String name;

    @Size(min = 3, max = 255, groups = {Create.class, Update.class})
    private String address;

    @Size(min = 5, max = 12, groups = {Create.class, Update.class})
    private String phone;

    @Positive(groups = {Create.class, Update.class})
    private Float payment;

    @NotNull(groups = {Create.class, Update.class})
    private Collection<Integer> courseIds;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private Integer version;
        private String name;
        private String address;
        private String phone;
        private Float payment;
        private Collection<Integer> courseIds;

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

        public Builder address(String address) {
            this.address = address;
            return this;
        }

        public Builder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder payment(Float payment) {
            this.payment = payment;
            return this;
        }

        public Builder courseIds(Collection<Integer> courseIds) {
            this.courseIds = courseIds;
            return this;
        }

        public ProfessorDto build() {
            ProfessorDto professorDto = new ProfessorDto();
            professorDto.setId(id);
            professorDto.setVersion(version);
            professorDto.setName(name);
            professorDto.setAddress(address);
            professorDto.setPhone(phone);
            professorDto.setPayment(payment);
            professorDto.setCourseIds(courseIds);
            return professorDto;
        }
    }
}
