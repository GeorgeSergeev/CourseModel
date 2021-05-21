package ru.khrebtov.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.khrebtov.university.entity.dtoEntity.DtoProfessor;
import ru.khrebtov.university.service.ProfessorService;

@Controller
@RequestMapping("/professors")
public class ProfessorControllerUi {

    private final ProfessorService professorService;

    @Autowired
    public ProfessorControllerUi(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping
    public String findAll(Model model) {
        model.addAttribute("professors", professorService.findAll());
        return "professor/professor";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        model.addAttribute("professor", professorService.findById(id));
        return "professor/professorById";
    }

    @GetMapping("/new")
    public String newProfessor(@ModelAttribute("professor") DtoProfessor professor) {
        return "professor/professor_new";
    }

    @PostMapping()
    public String create(@ModelAttribute("professor") DtoProfessor professor) {
        professorService.insert(professor);
        return "redirect:/professors";
    }
    @PutMapping()
    public String update(@ModelAttribute("professor") DtoProfessor professor) {
        professorService.update(professor);
        return "redirect:/professors";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("professor", professorService.findById(id));
        return "professor/professor_edit";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        professorService.deleteById(id);
        return "redirect:/professors";
    }


}
