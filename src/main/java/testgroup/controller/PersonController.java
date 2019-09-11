package testgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import testgroup.model.Student;
import testgroup.service.PersonService;

import java.util.List;

@Controller
public class PersonController {
    private PersonService personService;

    @Autowired
    public void setPersonService(PersonService personService){
        this.personService = personService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView allStudents() {
        List<Student> students = personService.allStudents();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("students");
        modelAndView.addObject("studentsList", students);
        return modelAndView;
    }

    @RequestMapping(value = "/listen/{studentID}", method = RequestMethod.GET)
    public ModelAndView listen(@PathVariable("studentID") int id){
        Student student = personService.studentByID(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("listenCourses");
        modelAndView.addObject("student", student);
        return modelAndView;
    }

}
