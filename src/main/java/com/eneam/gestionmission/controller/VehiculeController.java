package com.eneam.gestionmission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eneam.gestionmission.model.Vehicule;
import com.eneam.gestionmission.service.VehiculeService;

@Controller
@RequestMapping("/vehicules")
public class VehiculeController {
	
	@Autowired
    private VehiculeService vehiculeService;

    @GetMapping
    public String listerVehicules(Model model) {
        model.addAttribute("vehicules", vehiculeService.listerVehicules());
        return "vehicule/index";
    }

    @GetMapping("/nouveau")
    public String afficherFormulaireCreation(Model model) {
        model.addAttribute("vehicule", new Vehicule());
        return "vehicule/create";
    }

    @PostMapping
    public String creerVehicule(@ModelAttribute Vehicule vehicule,Model model) {
    	try {
            vehiculeService.creerVehicule(vehicule);
            model.addAttribute("message", "Véhicule ajouté avec succès.");
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }
        return "redirect:/vehicules";
    }

    @GetMapping("/{id}/modifier")
    public String afficherFormulaireModification(@PathVariable Long id, Model model) {
        Vehicule vehicule = vehiculeService.trouverVehiculeParId(id);
        if (vehicule != null) {
            model.addAttribute("vehicule", vehicule);
            return "vehicule/edit";
        } else {
            return "redirect:/vehicules";
        }
    }

    @PostMapping("/{id}/modifier")
    public String mettreAJourVehicule(@PathVariable Long id, @ModelAttribute Vehicule vehicule) {
        vehiculeService.mettreAJourVehicule(id, vehicule);
        return "redirect:/vehicules";
    }

    @GetMapping("/{id}/supprimer")
    public String supprimerVehicule(@PathVariable Long id) {
        vehiculeService.supprimerVehicule(id);
        return "redirect:/vehicules";
    }

}
