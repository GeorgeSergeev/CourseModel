package ru.tembaster.courses.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Data
@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    @NotBlank
    @NotNull
    @Column(name = "name")
    protected String name;

    @NotBlank
    @NotNull
    @Column(name = "address")
    protected String address;

    @NotBlank
    @NotNull
    @Column(name = "phone")
    @Min(value = 10)
    @Max(value = 10)
    protected String phone;

    protected AbstractNamedEntity() {
    }

    protected AbstractNamedEntity(Integer id, String name, String address, String phone) {
        super(id);
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractNamedEntity)) return false;
        AbstractNamedEntity that = (AbstractNamedEntity) o;
        return Objects.equals(id, that.id) && name.equals(that.name) && address.equals(that.address) && phone.equals(that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone);
    }
}
