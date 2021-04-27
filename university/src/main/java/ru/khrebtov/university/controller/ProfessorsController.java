package ru.khrebtov.university.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import ru.khrebtov.university.entity.dtoEntity.DtoProfessor;
import ru.khrebtov.university.service.ProfessorServiceRest;

import java.io.Serializable;
import java.util.List;

@RestController
public class ProfessorsController implements Serializable {


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

//    public void preloadData(ComponentSystemEvent componentSystemEvent) {
//        professors = professorService.findAll();
//    }
}
