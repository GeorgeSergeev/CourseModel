package ru.softlab.coursemodel.model.dto;

import lombok.Getter;
import lombok.Setter;
import ru.softlab.coursemodel.model.dto.validation.scenario.Create;
import ru.softlab.coursemodel.model.dto.validation.scenario.Update;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CompletingCourseDto extends BaseDto {

    private static final int MIN_MARK_VALUE = 1;
    private static final int MAX_MARK_VALUE = 5;

    @NotNull
    private Integer studentId;

    @NotNull
    private Integer courseId;

    @NotNull
    @Min(value = MIN_MARK_VALUE, groups = {Create.class, Update.class},
            message = "should be less then " + MIN_MARK_VALUE)
    @Max(value = MAX_MARK_VALUE, groups = {Create.class, Update.class},
            message = "should be less then " + MAX_MARK_VALUE)
    private Integer mark;

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Integer id;
        private Integer version;
        private Integer studentId;
        private Integer courseId;
        private Integer mark;

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

        public Builder studentId(Integer studentId) {
            this.studentId = studentId;
            return this;
        }

        public Builder courseId(Integer courseId) {
            this.courseId = courseId;
            return this;
        }

        public Builder mark(Integer mark) {
            this.mark = mark;
            return this;
        }

        public CompletingCourseDto build() {
            CompletingCourseDto completingCourseDto = new CompletingCourseDto();
            completingCourseDto.setId(id);
            completingCourseDto.setVersion(version);
            completingCourseDto.setStudentId(studentId);
            completingCourseDto.setCourseId(courseId);
            completingCourseDto.setMark(mark);
            return completingCourseDto;
        }
    }
}
