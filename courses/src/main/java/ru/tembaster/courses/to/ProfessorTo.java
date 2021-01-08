package ru.tembaster.courses.to;

import java.beans.ConstructorProperties;
import java.util.Objects;

public class ProfessorTo {

    private final Integer id;
    private final String name;
    private final String address;
    private final String phone;
    private final Double payment;
    private final Integer studentsCount;
    private final Double avgPerformance;

    @ConstructorProperties({"id", "name", "address", "phone", "payment", "studentsCount", "avgPerformance"})
    public ProfessorTo(Integer id, String name, String address, String phone, Double payment, Integer studentsCount,
                        Double avgPerformance) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.payment = payment;
        this.studentsCount = studentsCount;
        this.avgPerformance = avgPerformance;
    }

    public Integer getId() {
        return id;
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

    public Double getPayment() {
        return payment;
    }

    public Integer getStudentsCount() {
        return studentsCount;
    }

    public Double getAvgPerformance() {
        return avgPerformance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProfessorTo)) return false;
        ProfessorTo that = (ProfessorTo) o;
        return Objects.equals(id, that.id) &&
               Objects.equals(name, that.name) &&
               Objects.equals(address, that.address) &&
               Objects.equals(phone, that.phone) &&
               Objects.equals(payment, that.payment) &&
               Objects.equals(studentsCount, that.studentsCount) &&
               Objects.equals(avgPerformance, that.avgPerformance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, payment, studentsCount, avgPerformance);
    }

    @Override
    public String toString() {
        return "ProfessorTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", payment=" + payment +
                ", studentsCount=" + studentsCount +
                ", avgPerformance=" + avgPerformance +
                '}';
    }
}

