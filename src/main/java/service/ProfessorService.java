package service;

import dao.ProfessorDAO;
import dao.ProfessorDAOImpl;
import lombok.NoArgsConstructor;
import model.Professor;

@NoArgsConstructor
public class ProfessorService {

    private ProfessorDAO dao = new ProfessorDAOImpl();

    public Professor getById(int id) {
        return dao.findById(id);
    }

    public void addProfessor(Professor professor) {
        if (dao.findById(professor.getId()) == null) {
            dao.save(professor);
        } else {
            System.out.println("Professor already exist");
        }
    }

}
