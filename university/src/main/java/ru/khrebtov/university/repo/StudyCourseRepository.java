package ru.khrebtov.university.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.khrebtov.university.entity.Course;
import ru.khrebtov.university.entity.Student;
import ru.khrebtov.university.entity.StudyCourse;

import java.util.List;

@Repository
public class StudyCourseRepository {
    private final StudyCourseRepo studyCourseRepo;

    @Autowired
    public StudyCourseRepository(StudyCourseRepo studyCourseRepo) {
        this.studyCourseRepo = studyCourseRepo;
    }


    public List<StudyCourse> findAll() {
        return studyCourseRepo.findAll();
    }

    public StudyCourse findById(Long id) {
        return studyCourseRepo.findById(id).orElse(null);
    }

    public Long count() {
        return studyCourseRepo.count();
    }

    public void saveOrUpdate(StudyCourse studyCourse) {
        studyCourseRepo.save(studyCourse);
    }

    public void deleteById(Long id) {
        studyCourseRepo.deleteById(id);
    }

    public List<Integer> getRatings(Long id) {
        return studyCourseRepo.getRatings(id);
    }

    public Student getStudentByStudyCourseId(Long id) {
        return studyCourseRepo.getStudentByStudyCourseId(id);
    }

    public Course getCourseByStudyCourseId(Long id) {
        return studyCourseRepo.getCourseByStudyCourseId(id);
    }

    public Double getAverageRating(Long id) {
        return studyCourseRepo.getAverageRating(id);
    }

    public StudyCourse findByCourseIdAndStudentId(Long courseId, Long studentId) {
        return studyCourseRepo.findByCourseIdAndStudentId(courseId, studentId);
    }
}
