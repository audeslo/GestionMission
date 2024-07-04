package com.eneam.gestionmission.service.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneam.gestionmission.model.Mission;
import com.eneam.gestionmission.repository.MissionRepository;

@Service
public class MissionApiService {
	
	 @Autowired
	    private MissionRepository missionRepository;

	    public List<Mission> trouverMissionsParPeriode(LocalDate dateDebut, LocalDate dateFin) {
	        return missionRepository.findByDateDebutBetween(dateDebut, dateFin);
	    }

}
