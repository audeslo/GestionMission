package com.eneam.gestionmission.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eneam.gestionmission.model.Vehicule;
import com.eneam.gestionmission.service.api.VehiculeApiService;


@RestController
@RequestMapping("/api/vehicules")
public class VehiculeApiController {
	
	@Autowired
    private VehiculeApiService vehiculeService;

    @PostMapping
    public Vehicule enregistrerVehicule(@RequestBody Vehicule vehicule) {
        return vehiculeService.enregistrerVehicule(vehicule);
    }

}
