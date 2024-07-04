/**
 * 
 */
package com.eneam.gestionmission.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eneam.gestionmission.model.Conducteur;
import com.eneam.gestionmission.model.Mission;

/**
 * 
 */
@Repository
public interface MissionRepository extends JpaRepository<Mission, Long> {

	List<Mission> findByConducteurAndDateFinAfterAndDateDebutBefore(Conducteur conducteur, LocalDate dateDebut, LocalDate dateFin);
	List<Mission> findByDateDebutBetweenOrDateFinBetween(LocalDate dateDebut1, LocalDate dateFin1, LocalDate dateDebut2, LocalDate dateFin2);
	List<Mission> findByDateDebutBetween(LocalDate dateDebut, LocalDate dateFin);
	List<Mission> findAllByDateDebutBetween(LocalDate dateDebut, LocalDate dateFin);
	
	long count();
}
