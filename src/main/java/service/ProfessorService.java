package service;

import dao.ProfessorDAO;
import dao.ProfessorDAOImpl;
import lombok.NoArgsConstructor;
import model.Course;
import model.Professor;
import util.Services;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ProfessorService {

    private ProfessorDAO dao = new ProfessorDAOImpl();
    private CourseService courseService;

    public Professor getById(int id) {
        return dao.findById(id);
    }

    public Professor getByName(String name) {
        return dao.findByName(name);
    }

    public void addProfessor(Professor professor) {
        if (dao.findById(professor.getId()) == null) {
            dao.save(professor);
        } else {
            System.out.println("Professor already exist");
        }
    }

    public List<Professor> getAll() {
        return dao.findAll();
    }

    public void removeProfessor(Professor professor) {
        if (courseService == null) {
            courseService = Services.getInstance().getCourseService();
        }
        List<Course> tmp = new ArrayList<>(professor.getCourses());
        for (Course course :tmp) {
            courseService.removeProfessorFromCourse(course);
        }
        dao.delete(professor);
    }

}
