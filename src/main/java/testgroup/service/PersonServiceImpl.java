package testgroup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import testgroup.dao.PersonDAO;
import testgroup.model.Course;
import testgroup.model.Student;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService
{
    private PersonDAO personDAO;

    @Autowired
    public void setFilmDAO(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public List<Student> allStudents() {
        return personDAO.allStudents();
    }

    @Override
    public Student studentByID(int studentID){
        return personDAO.studentByID(studentID);
    }

    @Override
    public void enrollСourse(int studentID, Course course){
        personDAO.enrollCourse(studentID, course);
    }

}
