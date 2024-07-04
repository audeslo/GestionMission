package com.eneam.gestionmission.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "immatriculation")})
public class Vehicule {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long	id;
	
	private String	marque;
    private String	modele;
    private String	immatriculation;
    private LocalDate datemiseencirculation;
    private int		nombreplace;
    
    // Getters et Setters
    


	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	

	/**
	 * @return the marque
	 */
	public String getMarque() {
		return marque;
	}
	/**
	 * @param marque the marque to set
	 */
	public void setMarque(String marque) {
		this.marque = marque;
	}
	/**
	 * @return the modele
	 */
	public String getModele() {
		return modele;
	}
	/**
	 * @param modele the modele to set
	 */
	public void setModele(String modele) {
		this.modele = modele;
	}
	/**
	 * @return the immatriculation
	 */
	public String getImmatriculation() {
		return immatriculation;
	}
	/**
	 * @param immatriculation the immatriculation to set
	 */
	public void setImmatriculation(String immatriculation) {
		this.immatriculation = immatriculation;
	}

	/**
	 * @return the nombreplace
	 */
	public int getNombreplace() {
		return nombreplace;
	}
	/**
	 * @param nombreplace the nombreplace to set
	 */
	public void setNombreplace(int nombreplace) {
		this.nombreplace = nombreplace;
	}


	public void setId(Long id2) {
		// TODO Auto-generated method stub
		
	}



	public LocalDate getDatemiseencirculation() {
		return datemiseencirculation;
	}



	public void setDatemiseencirculation(LocalDate datemiseencirculation) {
		this.datemiseencirculation = datemiseencirculation;
	}

  
   
    

}
