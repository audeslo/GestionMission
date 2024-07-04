package com.eneam.gestionmission.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eneam.gestionmission.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
	
	long count();
	

}
