package max.mustafin.service;

import max.mustafin.dao.StudentDao;
import max.mustafin.model.Course;
import max.mustafin.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;
    @Transactional
    public void create(Student student) {
        studentDao.create(student);
    }
    @Transactional
    public void delete(Student student) {
        studentDao.delete(student);
    }
    @Transactional
    public void update(Student student) {
        studentDao.update(student);
    }
    @Transactional
    public List<Student> getAll() {
        return studentDao.getAll();
    }
    @Transactional
    public Student getByScoreBook(int scoreBookNumber) {
        return studentDao.getByScoreBook(scoreBookNumber);
    }
    @Transactional
    public void updateCourseList(int id, Course course) {
        Student student = studentDao.getByScoreBook(id);
        List<Course> courseList = student.getCourseList();
        courseList.add(course);
    }
    @Transactional
    public void merge(Student student) {
        studentDao.merge(student);
    }
}
