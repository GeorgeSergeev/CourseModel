package ru.tembaster.courses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tembaster.courses.model.Professor;
import ru.tembaster.courses.service.ProfessorService;

import javax.validation.Valid;

@Controller
@RequestMapping("/professors")
public class ProfessorController {

    ProfessorService professorService;

    @Autowired
    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @GetMapping("/all")
    public String getAll(Model model) {
        model.addAttribute("professors", professorService.getAll());
        return "professors";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id, Model model) {
        Professor professor = professorService.get(id);
        professorService.delete(professor);
        return "redirect:/professors/all";
    }

    @GetMapping("/signup")
    public String showSignUpForm(Professor professor) {
        return "addprofessor";
    }

    @PostMapping("/addprofessor")
    public String create(@Valid Professor professor, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "addprofessor";
        }
        professorService.save(professor);
        return "redirect:/professors/all";
    }


}
