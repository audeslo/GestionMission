package com.eneam.gestionmission.model;

import java.time.LocalDate;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Conducteur {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long	id;
    private String	nom;
    private String	prenom;
    private String	typepermis;
    private String	telephone;
    private LocalDate	datedenaissance;
    
    
   

	// Getters et Setters
	public Long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTypepermis() {
		return typepermis;
	}
	public void setTypepermis(String typepermis) {
		this.typepermis = typepermis;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}


	public LocalDate getDatedenaissance() {
		return datedenaissance;
	}

	public void setDatedenaissance(LocalDate datedenaissance) {
		this.datedenaissance = datedenaissance;
	}

	public void setId(Long id2) {
		// TODO Auto-generated method stub
		
	}

}
