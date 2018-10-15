package max.mustafin.service;

import max.mustafin.dao.CourseDao;
import max.mustafin.model.Course;
import max.mustafin.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseDao courseDao;
    @Transactional
    public void create(Course course) {
        courseDao.create(course);
    }
    @Transactional
    public void delete(Course course) {
        courseDao.delete(course);
    }
    @Transactional
    public void update(Course course) {
        courseDao.update(course);
    }
    @Transactional
    public List<Course> getAll() {
        return courseDao.getAll();
    }
    @Transactional
    public Course getByNumber(int number) {
        return courseDao.getByNumber(number);
    }
}
