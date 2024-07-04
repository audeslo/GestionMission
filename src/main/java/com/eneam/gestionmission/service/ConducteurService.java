/**
 * 
 */
package com.eneam.gestionmission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneam.gestionmission.model.Conducteur;
import com.eneam.gestionmission.repository.ConducteurRepository;

/**
 * 
 */
@Service
public class ConducteurService {
	
	@Autowired
    private ConducteurRepository conducteurRepository;

    public List<Conducteur> listerConducteurs() {
        return conducteurRepository.findAll();
    }

    public Conducteur creerConducteur(Conducteur conducteur) {
        return conducteurRepository.save(conducteur);
    }

    public Conducteur trouverConducteurParId(Long id) {
        return conducteurRepository.findById(id).orElse(null);
    }

    public Conducteur mettreAJourConducteur(Long id, Conducteur conducteur) {
        conducteur.setId(id);
        return conducteurRepository.save(conducteur);
    }

    public void supprimerConducteur(Long id) {
        conducteurRepository.deleteById(id);
    }
    
    public long getNombreDeConduteurs() {
        return conducteurRepository.count();
    }

}
