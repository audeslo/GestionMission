package com.eneam.gestionmission.model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"participants"})
public class Mission {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String destination;
    private String objetmission;
    
    @ElementCollection(fetch = FetchType.EAGER)
    private List <String> listparticipants;
    
    @ManyToOne
    private Conducteur conducteur;
    
    
    
    public List<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(List<Participant> participants) {
		this.participants = participants;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@ManyToOne
    private Vehicule vehicule;
    
	@ManyToMany
	@JoinTable(
	        name = "mission_participant",
	        joinColumns = @JoinColumn(name = "mission_id"),
	        inverseJoinColumns = @JoinColumn(name = "participant_id"))
    private List<Participant> participants;
    
    // Getters et Setters
    
    

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	

	/**
	 * @return the dateDebut
	 */
	public LocalDate getDateDebut() {
		return dateDebut;
	}

	/**
	 * @param dateDebut the dateDebut to set
	 */
	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}

	/**
	 * @return the dateFin
	 */
	public LocalDate getDateFin() {
		return dateFin;
	}

	/**
	 * @param dateFin the dateFin to set
	 */
	public void setDateFin(LocalDate dateFin) {
		this.dateFin = dateFin;
	}

	/**
	 * @return the destination
	 */
	public String getDestination() {
		return destination;
	}

	/**
	 * @param destination the destination to set
	 */
	public void setDestination(String destination) {
		this.destination = destination;
	}

	/**
	 * @return the objetmission
	 */
	public String getObjetmission() {
		return objetmission;
	}

	/**
	 * @param objetmission the objetmission to set
	 */
	public void setObjetmission(String objetmission) {
		this.objetmission = objetmission;
	}

	/**
	 * @return the listparticipants
	 */
	public List<String> getListparticipants() {
		return listparticipants;
	}

	/**
	 * @param listparticipants the listparticipants to set
	 */
	public void setListparticipants(List<String> listparticipants) {
		this.listparticipants = listparticipants;
	}

	/**
	 * @return the conducteur
	 */
	public Conducteur getConducteur() {
		return conducteur;
	}

	/**
	 * @param conducteur the conducteur to set
	 */
	public void setConducteur(Conducteur conducteur) {
		this.conducteur = conducteur;
	}

	/**
	 * @return the vehicule
	 */
	public Vehicule getVehicule() {
		return vehicule;
	}

	/**
	 * @param vehicule the vehicule to set
	 */
	public void setVehicule(Vehicule vehicule) {
		this.vehicule = vehicule;
	}

    
    

}
