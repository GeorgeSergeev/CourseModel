package ru.tembaster.courses.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@MappedSuperclass
public abstract class AbstractEntity {

    public static final int START_SEQ = 100_000;

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    Integer id;

    @NotNull
    @Column(name = "name")
    String name;

    @NotNull
    @Column(name = "address")
    String address;

    @NotNull
    @Column(name = "phone")
    String phone;

    protected AbstractEntity() {
    }

    @Override
    public String toString() {
        return "AbstractEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractEntity)) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.id) && name.equals(that.name) && address.equals(that.address) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone);
    }
}
