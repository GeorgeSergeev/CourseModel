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
import ru.tembaster.courses.to.ProfessorTo;
import ru.tembaster.courses.util.ExportToXlsUtil;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

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
        professorService.delete(id);
        return "redirect:/professors/all";
    }

    @GetMapping("/signup")
    public String showSignUpForm() {
        return "add-professor";
    }

    @PostMapping("/addprofessor")
    public String create(@Valid Professor professor, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "add-professor";
        }
        professorService.save(professor);
        return "redirect:/professors/all";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        Professor professor = professorService.get(id);
        model.addAttribute("professor", professor);
        model.addAttribute("professorStudents", professorService.countAllStudents(id));
        model.addAttribute("professorStudentsAvgPerformance", professorService.getAvgPerformance(id));
        return "update-professor";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") Integer id, @Valid Professor professor,
                         BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            professor.setId(id);
            return "update-professor";
        }
        professorService.save(professor);
        return "redirect:/professors/all";
    }

    @GetMapping("/excel")
    public void exportToExcel(HttpServletResponse response) {
        response.setHeader("Content-Disposition", "attachment; filename=file.xlsx");
        List<ProfessorTo> professorTos = professorService.getProfessorToList();
        ExportToXlsUtil exporter = new ExportToXlsUtil(professorTos);
        exporter.export(response);
    }
}
