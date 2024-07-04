/**
 * 
 */
package com.eneam.gestionmission.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneam.gestionmission.model.Mission;
import com.eneam.gestionmission.repository.MissionRepository;

/**
 * 
 */
@Service
public class MissionService {
	
	@Autowired
    private MissionRepository missionRepository;
	

    public List<Mission> listerMissions() {
        return missionRepository.findAll();
    }

    public Mission creerMission(Mission mission) {
        if (verifierConflitDePlanning(mission)) {
            throw new IllegalArgumentException("Le conducteur est déjà programmé pour une autre mission pendant cette période.");
        }
        return missionRepository.save(mission);
    }

    private boolean verifierConflitDePlanning(Mission mission) {
        List<Mission> missionsExistantes = missionRepository.findByConducteurAndDateFinAfterAndDateDebutBefore(
                mission.getConducteur(),
                mission.getDateDebut(),
                mission.getDateFin()
        );
        return !missionsExistantes.isEmpty();
    }
    
    public List<Mission> trouverMissionsParPeriode(LocalDate dateDebut, LocalDate dateFin) {
        return missionRepository.findAllByDateDebutBetween(dateDebut, dateFin);
    }

    public Mission trouverMissionParId(Long id) {
        return missionRepository.findById(id).orElse(null);
    }

    public Mission mettreAJourMission(Long id, Mission mission) {
        mission.setId(id);
        return missionRepository.save(mission);
    }

    public void supprimerMission(Long id) {
        missionRepository.deleteById(id);
    }
    
    public long getNombreDeMissions() {
        return missionRepository.count();
    }

}
