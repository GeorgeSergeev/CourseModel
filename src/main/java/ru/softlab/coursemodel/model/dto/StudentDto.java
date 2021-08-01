package ru.softlab.coursemodel.model.dto;

import lombok.Getter;
import lombok.Setter;
import ru.softlab.coursemodel.model.dto.validation.scenario.Create;
import ru.softlab.coursemodel.model.dto.validation.scenario.Update;

import javax.validation.constraints.*;
import java.util.Collection;

@Getter
@Setter
public class StudentDto extends BaseDto {

    @NotBlank(groups = {Create.class, Update.class})
    @Size(min = 3, groups = {Create.class, Update.class})
    private String name;

    @Size(max = 255, groups = {Create.class, Update.class})
    private String address;

    @NotBlank(groups = {Create.class, Update.class})
    @Size(min = 5, max = 10, groups = {Create.class, Update.class})
    private String phone;

    @NotBlank(groups = {Create.class, Update.class})
    @Size(min = 5, max = 30, groups = {Create.class, Update.class})
    private String email;

    @Positive(groups = {Create.class, Update.class})
    @Max(value = Integer.MAX_VALUE, groups = {Create.class, Update.class},
            message = "should be less then " + Integer.MAX_VALUE)
    private Integer recordBookNumber;

    private Float avgPerformance;

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
        private String email;
        private Integer recordBookNumber;
        private Float avgPerformance;
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

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder recordBookNumber(Integer recordBookNumber) {
            this.recordBookNumber = recordBookNumber;
            return this;
        }

        public Builder avgPerformance(Float avgPerformance) {
            this.avgPerformance = avgPerformance;
            return this;
        }

        public Builder courseIds(Collection<Integer> courseIds) {
            this.courseIds = courseIds;
            return this;
        }

        public StudentDto build() {
            StudentDto studentDto = new StudentDto();
            studentDto.setId(id);
            studentDto.setVersion(version);
            studentDto.setName(name);
            studentDto.setAddress(address);
            studentDto.setPhone(phone);
            studentDto.setEmail(email);
            studentDto.setRecordBookNumber(recordBookNumber);
            studentDto.setAvgPerformance(avgPerformance);
            studentDto.setCourseIds(courseIds);
            return studentDto;
        }
    }
}
