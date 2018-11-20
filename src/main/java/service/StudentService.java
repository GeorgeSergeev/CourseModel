package service;

import dao.StudentDAO;
import dao.StudentDAOImpl;
import lombok.NoArgsConstructor;
import model.Student;

@NoArgsConstructor
public class StudentService {

    private StudentDAO dao = new StudentDAOImpl();

    public void addStudent(Student student) {
        dao.save(student);
    }

}
