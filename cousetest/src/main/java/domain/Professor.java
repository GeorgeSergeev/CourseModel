package domain;

import com.sun.istack.internal.NotNull;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Professor {
    private final String name;
    private final String address;
    private final String phone;
    private final float salary;

    public Professor()
    {
        this("","","",0);
    }
    public Professor(@NotNull String name, @NotNull String address, @NotNull String phone, float salary) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public float getSalary() {
        return salary;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.name)
                .append(this.address)
                .append(this.phone)
                .append(this.salary)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Professor){
            final Professor other = (Professor) obj;
            return new EqualsBuilder()
                    .append(this.name,other.name)
                    .append(this.address,other.address)
                    .append(this.phone,other.phone)
                    .append(this.salary,other.salary)
                    .isEquals();
        } else{
            return false;
        }
    }
}
