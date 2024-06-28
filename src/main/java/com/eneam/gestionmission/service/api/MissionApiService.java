package com.eneam.gestionmission.service.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneam.gestionmission.model.Mission;
import com.eneam.gestionmission.repository.MissionRepository;

@Service
public class MissionApiService {
	
	 @Autowired
	    private MissionRepository missionRepository;

	    public List<Mission> trouverMissionsParPeriode(LocalDateTime dateDebut, LocalDateTime dateFin) {
	        return missionRepository.findByDateDebutBetween(dateDebut, dateFin);
	    }

}
