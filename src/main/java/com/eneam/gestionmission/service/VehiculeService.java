package com.eneam.gestionmission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneam.gestionmission.model.Vehicule;
import com.eneam.gestionmission.repository.VehiculeRepository;

@Service
public class VehiculeService {
	
	@Autowired
    private VehiculeRepository vehiculeRepository;

    public List<Vehicule> listerVehicules() {
        return vehiculeRepository.findAll();
    }

    public Vehicule creerVehicule(Vehicule vehicule) throws Exception {
        if (vehiculeRepository.existsByImmatriculation(vehicule.getImmatriculation())) {
            throw new Exception("Un véhicule avec cette immatriculation existe déjà.");
        }
        return vehiculeRepository.save(vehicule);
    }

    public Vehicule trouverVehiculeParId(Long id) {
        return vehiculeRepository.findById(id).orElse(null);
    }

    public Vehicule mettreAJourVehicule(Long id, Vehicule vehicule) {
        vehicule.setId(id);
        return vehiculeRepository.save(vehicule);
    }

    public void supprimerVehicule(Long id) {
        vehiculeRepository.deleteById(id);
    }

    public long getNombreDeVehicules() {
        return vehiculeRepository.count();
    }
}
