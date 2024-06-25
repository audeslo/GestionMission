/**
 * 
 */
package com.eneam.gestionmission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eneam.gestionmission.model.Participant;
import com.eneam.gestionmission.service.ParticipantService;


/**
 * 
 */
@Controller
@RequestMapping("/participants")
public class ParticipantController {
	@Autowired
    private ParticipantService participantService;

    @GetMapping
    public String listerParticipants(Model model) {
        model.addAttribute("participants", participantService.listerParticipants());
        return "participant/index";
    }

    @GetMapping("/nouveau")
    public String afficherFormulaireCreation(Model model) {
        model.addAttribute("participant", new Participant());
        return "participant/create";
    }

    @PostMapping
    public String creerParticipant(@ModelAttribute Participant participant) {
        participantService.creerParticipant(participant);
        return "redirect:/participants";
    }

    @GetMapping("/{id}/modifier")
    public String afficherFormulaireModification(@PathVariable Long id, Model model) {
        Participant participant = participantService.trouverParticipantParId(id);
        if (participant != null) {
            model.addAttribute("participant", participant);
            return "participant/edit";
        } else {
            return "redirect:/participants";
        }
    }

    @PostMapping("/{id}/modifier")
    public String mettreAJourParticipant(@PathVariable Long id, @ModelAttribute Participant participant) {
        participantService.mettreAJourParticipant(id, participant);
        return "redirect:/index";
    }

    @GetMapping("/{id}/supprimer")
    public String supprimerParticipant(@PathVariable Long id) {
        participantService.supprimerParticipant(id);
        return "redirect:/participants";
    }

}
