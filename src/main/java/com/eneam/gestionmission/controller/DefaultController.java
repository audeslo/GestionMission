package com.eneam.gestionmission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eneam.gestionmission.service.ConducteurService;
import com.eneam.gestionmission.service.MissionService;
import com.eneam.gestionmission.service.ParticipantService;
import com.eneam.gestionmission.service.VehiculeService;

@Controller
@RequestMapping("/")
public class DefaultController {
	
	@Autowired
    private ConducteurService conducteurService;
	
	@Autowired
    private VehiculeService vehiculeService;
	
	@Autowired
    private ParticipantService participantService;
	
	@Autowired
    private MissionService missionService;
	
	 @GetMapping
	    public String accueil(Model model) {
		 
		 model.addAttribute("conducteurs", conducteurService.getNombreDeConduteurs());
		 model.addAttribute("vehicules", vehiculeService.getNombreDeVehicules());
		 model.addAttribute("participants", participantService.getNombreDeParticipants());
		 model.addAttribute("missions", missionService.getNombreDeMissions());
	        return "dashboard";
	    }

}
