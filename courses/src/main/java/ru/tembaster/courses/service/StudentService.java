package ru.tembaster.courses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tembaster.courses.model.Course;
import ru.tembaster.courses.model.CourseProgress;
import ru.tembaster.courses.model.Student;
import ru.tembaster.courses.repository.CourseProgressRepository;
import ru.tembaster.courses.repository.StudentRepository;
import ru.tembaster.courses.util.StudentUtil;

import java.util.Collections;
import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;
    CourseProgressRepository courseProgressRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository, CourseProgressRepository courseProgressRepository) {
        this.studentRepository = studentRepository;
        this.courseProgressRepository = courseProgressRepository;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public boolean delete(int id) {
        if (isValid(id)) {
            studentRepository.delete(get(id));
            return true;
        }
        return false;
    }

    public Student get(int id) {
        return studentRepository.findById(id);
    }

    public List<Course> getWithCourses(Integer id) {
        if (!isValid(id)) {
            return Collections.emptyList();
        }
        return studentRepository.getWithCourses(id);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public boolean assignCourse(Student student, Course course) {
        if (student != null && course != null) {
            CourseProgress cp = new CourseProgress(student, course);
            courseProgressRepository.save(cp);
            return true;
        } else {
            return false;
        }
    }

    public boolean setMarkInCourseProgress(CourseProgress courseProgress, Integer mark) {
        if (courseProgress != null && mark != null) {
            courseProgress.setMark(mark);
            return true;
        } else {
            return false;
        }
    }

    public boolean setAvgPerformance(Student student, Double avgPerformance) {
        Student updateCandidate = studentRepository.findById(student.getId());
        if (updateCandidate != null && avgPerformance != null) {
            Double mark = StudentUtil.getAvgPerformanceByStudents(Collections.singleton(student));
            studentRepository.findById(student.getId()).setAvgPerformance(mark);
            return true;
        } else {
            return false;
        }
    }

    private boolean isValid(Integer id) {
        return id != null && studentRepository.findById(id) !=null;
    }
}
