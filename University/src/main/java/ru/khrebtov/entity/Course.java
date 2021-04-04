package ru.khrebtov.entity;


import ru.khrebtov.entity.dtoEntity.DtoCourse;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "course")
@NamedQueries({
        @NamedQuery(name = "findAllCourse", query = "from Course "),
        @NamedQuery(name = "countAllCourse", query = "select count(*) from Course "),
        @NamedQuery(name = "deleteCourseById", query = "delete from Course c where c.id = :id"),
        @NamedQuery(name = "findCourseByNumber", query = "from Course c where c.number = :number"),
        @NamedQuery(name = "findCourseById", query = "from Course c where c.id = :id"),
        @NamedQuery(name = "getCourseStudents", query = "select s from Student s left join StudyCourse cs " +
                "on s.id=cs.student.id where cs.course.id = :courseId"),
        @NamedQuery(name = "getCountCourseStudents", query = "select count(*) from Student s left join StudyCourse cs " +
                "on s.id=cs.student.id where cs.course.id = :courseId"),
        @NamedQuery(name = "deleteStudentFromCourse", query = "delete from StudyCourse cs " +
                "where cs.course.id=:courseId AND cs.student.id=:studentId"),
        @NamedQuery(name = "getCourseStudy", query = "select sc from StudyCourse sc  where sc.course.id = :courseId "),
        @NamedQuery(name = "getCourseProfessor", query = "select p from Professor p left join CourseProfessor cp" +
                " on p.id=cp.professorsId where cp.courseId = :courseId")
})
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;
    @Column
    private int number;
    @Column
    private float cost;


    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "study_course",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;

    @OneToMany(mappedBy = "course")
    @Transient
    private Set<StudyCourse> studyCourses;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "course_professor",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "professors_id")
    )
    private Set<Professor> professors;


    public Course() {
    }

    public Course(Long id, String name, int number, float cost) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.cost = cost;
    }

    public Course(DtoCourse course) {
        this(course.getId(), course.getName(), course.getNumber(), course.getCost());

        if (course.getStudyCourses() != null) {
            this.studyCourses = new HashSet<>();
            course.getStudyCourses().forEach(studyCourse -> studyCourses.add(new StudyCourse(studyCourse)));
        }

        if (course.getStudents() != null) {
            this.students = new HashSet<>();
            course.getStudents().forEach(student -> students.add(new Student(student)));
        }

        if (course.getProfessors() != null) {
            this.professors = new HashSet<>();
            course.getProfessors().forEach(p -> professors.add(new Professor(p)));
        }

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    public Set<StudyCourse> getStudyCourses() {
        return studyCourses;
    }

    public void setStudyCourses(Set<StudyCourse> studyCourses) {
        this.studyCourses = studyCourses;
    }

    public Set<Professor> getProfessors() {
        return professors;
    }

    public void setProfessors(Set<Professor> professors) {
        this.professors = professors;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", cost=" + cost +
                '}';
    }
}
