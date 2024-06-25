/**
 * 
 */
package com.eneam.gestionmission.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.eneam.gestionmission.model.Mission;
import com.eneam.gestionmission.service.ConducteurService;
import com.eneam.gestionmission.service.MissionService;
import com.eneam.gestionmission.service.ParticipantService;
import com.eneam.gestionmission.service.VehiculeService;

/**
 * 
 */

@Controller
@RequestMapping("/missions")
public class MissionController {
	
	@Autowired
    private MissionService missionService;

    @Autowired
    private ConducteurService conducteurService;

    @Autowired
    private VehiculeService vehiculeService;

    @Autowired
    private ParticipantService participantService;

    @GetMapping
    public String listerMissions(Model model) {
        model.addAttribute("missions", missionService.listerMissions());
        return "mission/index";
    }

    @GetMapping("/nouveau")
    public String afficherFormulaireCreation(Model model) {
        model.addAttribute("mission", new Mission());
        model.addAttribute("conducteurs",conducteurService.listerConducteurs());
        model.addAttribute("vehicules", vehiculeService.listerVehicules());
        model.addAttribute("participants", participantService.listerParticipants());
        return "mission/create";
    }

    @PostMapping
    public String creerMission(@ModelAttribute Mission mission, Model model) {
    	
    	 try {
             missionService.creerMission(mission);
         } catch (IllegalArgumentException e) {
             model.addAttribute("errorMessage", e.getMessage());
             model.addAttribute("conducteurs", conducteurService.listerConducteurs());
             model.addAttribute("vehicules", vehiculeService.listerVehicules());
             model.addAttribute("participants", participantService.listerParticipants());
             return "mission/create";
         }
         return "redirect:/missions";
    }

    @GetMapping("/{id}/modifier")
    public String afficherFormulaireModification(@PathVariable Long id, Model model) {
        Mission mission = missionService.trouverMissionParId(id);
        if (mission != null) {
            model.addAttribute("mission", mission);
            model.addAttribute("conducteurs", conducteurService.listerConducteurs());
            model.addAttribute("vehicules", vehiculeService.listerVehicules());
            model.addAttribute("participants", participantService.listerParticipants());
            return "mission/edit";
        } else {
            return "redirect:/missions";
        }
    }

    @PostMapping("/{id}/modifier")
    public String mettreAJourMission(@PathVariable Long id, @ModelAttribute Mission mission) {
        missionService.mettreAJourMission(id, mission);
        return "redirect:/missions";
    }

    @GetMapping("/{id}/supprimer")
    public String supprimerMission(@PathVariable Long id) {
        missionService.supprimerMission(id);
        return "redirect:/missions";
    }
    
    
    @GetMapping("/recherche")
    public String afficherFormulaireRecherche() {
        return "mission/form_search";
    }

    @PostMapping("/recherche")
    public String rechercherMissionsParPeriode(@RequestParam("dateDebut") String dateDebut,
                                               @RequestParam("dateFin") String dateFin,
                                               Model model) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime dateDebutParsed = LocalDateTime.parse(dateDebut, formatter);
        LocalDateTime dateFinParsed = LocalDateTime.parse(dateFin, formatter);

        List<Mission> missions = missionService.trouverMissionsParPeriode(dateDebutParsed, dateFinParsed);
        model.addAttribute("missions", missions);
        return "mission/list_mission";
    }

}
