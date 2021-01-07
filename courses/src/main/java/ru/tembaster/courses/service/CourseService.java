package ru.tembaster.courses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tembaster.courses.model.Course;
import ru.tembaster.courses.model.Student;
import ru.tembaster.courses.repository.CourseRepository;
import ru.tembaster.courses.repository.StudentJpaRepository;

import java.util.List;
import java.util.Set;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final StudentJpaRepository studentJpaRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, StudentJpaRepository studentJpaRepository) {
        this.courseRepository = courseRepository;
        this.studentJpaRepository = studentJpaRepository;
    }

    public Course save(Course course) {
        return courseRepository.saveAndFlush(course);
    }

    public Course get(int id) {
        return courseRepository.findById(id).orElse(null);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

    public boolean delete(int id) {
        Course course = courseRepository.findById(id).orElse(null);
        if (course != null) {
            courseRepository.delete(course);
            return true;
        }
        return false;
    }

    public boolean deleteStudentFromCourse(int studentId, int courseId) {
        Set<Student> students = courseRepository.findById(courseId).get().getStudents();
        for (Student st : students) {
            if (st.getId().equals(studentId)) {
                students.remove(st);
                return true;
            }
        }
        return false;
    }

    public Student addStudentToCourse(int studentId, int courseId) {
        Set<Student> students = courseRepository.findById(courseId).get().getStudents();
        Student studentToAdd = studentJpaRepository.getOne(studentId);
        if (!students.contains(studentToAdd)) {
            students.add(studentToAdd);
            return studentToAdd;
        } else {
            return null;
        }
    }
}
