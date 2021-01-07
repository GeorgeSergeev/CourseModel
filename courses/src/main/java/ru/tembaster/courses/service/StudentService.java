package ru.tembaster.courses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tembaster.courses.model.Course;
import ru.tembaster.courses.model.CourseProgress;
import ru.tembaster.courses.model.Student;
import ru.tembaster.courses.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {

    StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public boolean delete(int id) {
        Student student = studentRepository.findById(id);
        if (student != null) {
            studentRepository.delete(student);
            return true;
        }
        return false;
    }

    public Student get(int id) {
        return studentRepository.findById(id);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }

    public boolean assignCourse(Student student, Course course) {
        if (student != null && course != null) {
            //TODO implement assign student to course
            return true;
        } else {
            return false;
        }
    }

    public boolean setMarkInCourseProgress(CourseProgress courseProgress, Integer mark) {
        if (courseProgress != null) {
            //TODO implement set mark to courseprogress
            return true;
        } else {
            return false;
        }
    }

    public boolean setAvgPerformance(Student student, Double avgPerformance) {
        if (student != null) {
            //TODO implement set avgPerformance to student
            return true;
        } else {
            return false;
        }
    }
}
