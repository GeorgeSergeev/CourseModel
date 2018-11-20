package service;

import dao.ProfessorDAO;
import dao.ProfessorDAOImpl;
import lombok.NoArgsConstructor;
import model.Professor;

@NoArgsConstructor
public class ProfessorService {

    private ProfessorDAO dao = new ProfessorDAOImpl();

    public void addProfessor(Professor professor) {
        dao.save(professor);
    }

}
