package com.eneam.gestionmission.service.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneam.gestionmission.model.Vehicule;
import com.eneam.gestionmission.repository.VehiculeRepository;

@Service
public class VehiculeApiService {
	
	 @Autowired
	    private VehiculeRepository vehiculeRepository;

	    public Vehicule enregistrerVehicule(Vehicule vehicule) {
	        return vehiculeRepository.save(vehicule);
	    }

}
