/**
 * 
 */
package com.eneam.gestionmission.repository;

import java.time.LocalDateTime;
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

	List<Mission> findByConducteurAndDateFinAfterAndDateDebutBefore(Conducteur conducteur, LocalDateTime dateDebut, LocalDateTime dateFin);
	List<Mission> findByDateDebutBetweenOrDateFinBetween(LocalDateTime dateDebut1, LocalDateTime dateFin1, LocalDateTime dateDebut2, LocalDateTime dateFin2);
	List<Mission> findByDateDebutBetween(LocalDateTime dateDebut, LocalDateTime dateFin);
}
