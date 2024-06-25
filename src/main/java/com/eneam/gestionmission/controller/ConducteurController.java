package com.eneam.gestionmission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eneam.gestionmission.model.Conducteur;
import com.eneam.gestionmission.service.ConducteurService;

@Controller
@RequestMapping("/conducteurs")
public class ConducteurController {
	@Autowired
    private ConducteurService conducteurService;

    @GetMapping
    public String listerConducteurs(Model model) {
        model.addAttribute("conducteurs", conducteurService.listerConducteurs());
        return "conducteur/index";
    }

    @GetMapping("/nouveau")
    public String afficherFormulaireCreation(Model model) {
        model.addAttribute("conducteur", new Conducteur());
        return "conducteur/create";
    }

    @PostMapping
    public String creerConducteur(@ModelAttribute Conducteur conducteur) {
        conducteurService.creerConducteur(conducteur);
        return "redirect:/conducteurs";
    }

    @GetMapping("/{id}/modifier")
    public String afficherFormulaireModification(@PathVariable Long id, Model model) {
        Conducteur conducteur = conducteurService.trouverConducteurParId(id);
        if (conducteur != null) {
            model.addAttribute("conducteur", conducteur);
            return "conducteur/edit";
        } else {
            return "redirect:/conducteurs";
        }
    }

    @PostMapping("/{id}/modifier")
    public String mettreAJourConducteur(@PathVariable Long id, @ModelAttribute Conducteur conducteur) {
        conducteurService.mettreAJourConducteur(id, conducteur);
        return "redirect:/conducteurs";
    }

    @GetMapping("/{id}/supprimer")
    public String supprimerConducteur(@PathVariable Long id) {
        conducteurService.supprimerConducteur(id);
        return "redirect:/conducteurs";
    }

}
