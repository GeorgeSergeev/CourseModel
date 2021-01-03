package com.rstyle.softlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rstyle.softlab.models.Professor;
import com.rstyle.softlab.services.ProfessorService;

@Controller
public class TableController {
	
	@Autowired 
	private ProfessorService pSrvc;
	
	@GetMapping("/table")
	public String table(Model model) {
        model.addAttribute("data", pSrvc.findAll());
        return "table";
	}

	@PostMapping("/put")
	@ResponseBody
	public String put(Professor p) {
		System.out.println(p.getId());
		return "ok";
	}
}
