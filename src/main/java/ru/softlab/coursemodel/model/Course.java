package ru.softlab.coursemodel.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course extends BaseEntity {

    @Column
    private String title;

    @Column
    private Integer number;

    @Column
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

        public Course build() {
            Course course = new Course();
            course.setId(id);
            course.setVersion(version);
            course.setTitle(title);
            course.setNumber(number);
            course.setPrice(price);
            return course;
        }
    }
}
