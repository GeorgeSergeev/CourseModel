
import com.google.gson.annotations.SerializedName;

/**
 * Created by TheEndl on 01.05.2017.
 */



public class Teacher {

    public String name;

    public String address;

    public String phone;

    public Float payment;

    public Teacher(String name, String address, String phone, Float payment) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.payment = payment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Teacher teacher = (Teacher) o;

        if (!name.equals(teacher.name)) return false;
        if (!address.equals(teacher.address)) return false;
        if (!phone.equals(teacher.phone)) return false;
        return payment.equals(teacher.payment);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + phone.hashCode();
        result = 31 * result + payment.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", payment=" + payment +
                '}';
    }
}
