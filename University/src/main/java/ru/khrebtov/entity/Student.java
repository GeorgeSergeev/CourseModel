package ru.khrebtov.entity;

import javax.persistence.*;

@Entity
@Table(name = "students")
@NamedQueries({
        @NamedQuery(name = "findAll", query = "from Student"),
        @NamedQuery(name = "countAll", query = "select count(*) from Student "),
        @NamedQuery(name = "deleteById", query = "delete from Student s where s.id = :id"),
        @NamedQuery(name = "findByName", query = "from Student s where s.name = :name"),
        @NamedQuery(name = "findById", query = "from Student s where s.id = :id")
})
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String address;
    @Column(unique = true)
    private String phone;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private Integer recordBook;
    @Column
    private float progress;

    public Student() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getRecordBook() {
        return recordBook;
    }

    public void setRecordBook(Integer recordBook) {
        this.recordBook = recordBook;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
