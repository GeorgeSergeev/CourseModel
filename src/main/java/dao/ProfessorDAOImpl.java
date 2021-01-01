package dao;

import lombok.NoArgsConstructor;
import model.Professor;
import util.SessionInstance;

import java.util.List;

@NoArgsConstructor
public class ProfessorDAOImpl extends AbstractDAOImpl<Professor> implements ProfessorDAO {

    @Override
    public Professor findByName(String name) {
        List<Professor> list = ((List<Professor>) SessionInstance.getInstance().getSession()
                .createQuery("FROM Professor WHERE name = '" + name + "'").list());
        if (list.size() == 1) {
            return list.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Professor findById(int id) {
        Professor professor = SessionInstance.getInstance().getSession()
                .get(Professor.class, id);
        return professor;
    }

    @Override
    public List<Professor> findAll() {
        List<Professor> professors = SessionInstance.getInstance().getSession()
                .createQuery("FROM Professor")
                .list();
        return professors;
    }
}
