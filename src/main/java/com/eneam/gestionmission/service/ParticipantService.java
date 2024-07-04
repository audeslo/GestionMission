/**
 * 
 */
package com.eneam.gestionmission.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eneam.gestionmission.model.Participant;
import com.eneam.gestionmission.repository.ParticipantRepository;

/**
 * 
 */

@Service
public class ParticipantService {
	
	 @Autowired
	    private ParticipantRepository participantRepository;

	    public List<Participant> listerParticipants() {
	        return participantRepository.findAll();
	    }

	    public Participant creerParticipant(Participant participant) {
	        return participantRepository.save(participant);
	    }

	    public Participant trouverParticipantParId(Long id) {
	        return participantRepository.findById(id).orElse(null);
	    }

	    public Participant mettreAJourParticipant(Long id, Participant participant) {
	        participant.setId(id);
	        return participantRepository.save(participant);
	    }

	    public void supprimerParticipant(Long id) {
	        participantRepository.deleteById(id);
	    }
	    
	    public long getNombreDeParticipants() {
	        return participantRepository.count();
	    }

}
