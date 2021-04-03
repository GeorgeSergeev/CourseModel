package ru.khrebtov.entity;


import ru.khrebtov.entity.dtoEntity.DtoStudent;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "students")
@NamedQueries({
        @NamedQuery(name = "findAll", query = "from Student"),
        @NamedQuery(name = "countAll", query = "select count(*) from Student "),
        @NamedQuery(name = "deleteById", query = "delete from Student s where s.id = :id"),
        @NamedQuery(name = "findByName", query = "from Student s where s.name = :name"),
        @NamedQuery(name = "findById", query = "from Student s where s.id = :id"),
        @NamedQuery(name = "getStudentCourses", query = "select c from Course c left join StudyCourse cs " +
                "on c.id=cs.course.id where cs.student.id = :studentId"),
        @NamedQuery(name = "getStudentStudyCourse", query = "from StudyCourse sc where sc.student.id = :studentId")
})
public class Student implements Serializable {
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
    private Float progress;

    @ManyToMany(mappedBy = "students")
    @Transient
    private Set<Course> courses;

    @OneToMany(mappedBy = "student")
    @Transient
    private Set<StudyCourse> studyCourses;


    public Student() {
    }

    public Student(Long id, String name, String address, String phone, String email, Integer recordBook,
                   Float progress) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.recordBook = recordBook;
        this.progress = progress;
    }

    public Student(DtoStudent student) {
        this(student.getId(), student.getName(), student.getAddress(), student.getPhone(), student.getEmail(),
                student.getRecordBook(), student.getProgress());

        if (student.getStudyCourses() != null) {
            this.studyCourses = new HashSet<>();
            student.getStudyCourses().forEach(sc -> studyCourses.add(new StudyCourse(sc)));
        }
        if (student.getCourses() != null) {
            this.courses = new HashSet<>();
            student.getCourses().forEach(c -> courses.add(new Course(c)));
        }

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

    public Float getProgress() {
        return progress;
    }

    public void setProgress(Float progress) {
        this.progress = progress;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    public Set<StudyCourse> getStudyCourses() {
        return studyCourses;
    }

    public void setStudyCourses(Set<StudyCourse> studyCourses) {
        this.studyCourses = studyCourses;
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
