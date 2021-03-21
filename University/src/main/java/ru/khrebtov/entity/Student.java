package ru.khrebtov.entity;

import ru.khrebtov.entity.DTOentity.StudentRepr;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

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

    @ManyToMany(fetch = FetchType.EAGER,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "students_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "courses_id")
    )
    private Set<Course> courses;

    public Student() {
    }

    public Student(Long id, String name, String address, String phone, String email, Integer recordBook, float progress) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.recordBook = recordBook;
        this.progress = progress;
    }

    public Student(StudentRepr student) {
        this(student.getId(), student.getName(), student.getAddress(), student.getPhone(), student.getEmail(), student.getRecordBook(), student.getProgress());
        courses = new HashSet<>();
        student.getCourses().forEach(c->courses.add(new Course(c)));
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

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Float.compare(student.progress, progress) == 0 && Objects.equals(id, student.id) && Objects.equals(name, student.name) && Objects.equals(address, student.address) && Objects.equals(phone, student.phone) && Objects.equals(email, student.email) && Objects.equals(recordBook, student.recordBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, phone, email, recordBook, progress);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", recordBook=" + recordBook +
                ", progress=" + progress +
                '}';
    }
}
