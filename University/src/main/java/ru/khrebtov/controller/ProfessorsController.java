package ru.khrebtov.controller;

import ru.khrebtov.entity.dtoEntity.DtoProfessor;
import ru.khrebtov.rest.ProfessorServiceRest;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named
public class ProfessorsController implements Serializable {

    @EJB
    private ProfessorServiceRest professorService;

    private List<DtoProfessor> professors;
    private DtoProfessor professor;

    public String createProfessor() {
        this.professor = new DtoProfessor();
        return "/professor_form.xhtml?faces-redirect=true";
    }

    public List<DtoProfessor> getAllProfessors() {
        return professors;
    }

    public String editProfessor(DtoProfessor professor) {
        this.professor = professor;
        return "/professor_form.xhtml?faces-redirect=true";
    }

    public void deleteProfessor(DtoProfessor professor) {
        professorService.deleteById(professor.getId());
    }

    public String saveProfessor() {
        professorService.saveOrUpdate(professor);
        return "/professor.xhtml?faces-redirect=true";
    }

    public DtoProfessor getProfessor() {
        return professor;
    }

    public void setProfessor(DtoProfessor professor) {
        this.professor = professor;
    }

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        professors = professorService.findAll();
    }
}
