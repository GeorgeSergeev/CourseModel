package controller;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import model.Professor;
import model.Student;
import service.JSONService;
import service.MainListsHolder;

@Controller
public class ProfessorActionController {

	@Autowired
	ServletContext context;

	@RequestMapping(value = "/professor", method = RequestMethod.GET)
	public String openProfessorActionPage(@RequestParam(value = "t") int actionType,
			@RequestParam(value = "id") String professorPhoneNum, Model model) {

		Map<String, Professor> professors = MainListsHolder.getProfessors(context);

		if (professors.containsKey(professorPhoneNum) || actionType == MainController.ADD_ACTION_TYPE) {

			switch (actionType) {

			case MainController.DELETE_ACTION_TYPE:

				professors.remove(professorPhoneNum);

				JSONService.writeProfessors(professors.values(), context);

				return "redirect:/professors";
			case MainController.EDIT_ACTION_TYPE:
				Professor professor = professors.get(professorPhoneNum);
				model.addAttribute("professor", professor);

				return "professor";

			case MainController.ADD_ACTION_TYPE:

				model.addAttribute("professor", new Professor());

				return "professor";
			}
		}

		return "professors";
	}

	@PostMapping("/professor")
	public String saveStudentData(@Valid Professor professor, BindingResult bindingResult) {

		Map<String, Professor> professors = MainListsHolder.getProfessors(context);

		if (professors.containsKey(professor.getTelephone())) {
			professors.remove(professor.getTelephone());
		}

		professors.put(professor.getTelephone(),professor);

		JSONService.writeProfessors(professors.values(), context);

		return "redirect:/professors";

	}

}
