/**
 * 
 */
package com.eneam.gestionmission.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.eneam.gestionmission.model.Mission;
import com.eneam.gestionmission.service.ConducteurService;
import com.eneam.gestionmission.service.MissionService;
import com.eneam.gestionmission.service.ParticipantService;
import com.eneam.gestionmission.service.ReportService;
import com.eneam.gestionmission.service.VehiculeService;


/**
 * 
 */

@Controller
@RequestMapping("/missions")

public class MissionController {

	@Autowired
	private MissionService missionService;

	@Autowired
	private ConducteurService conducteurService;

	@Autowired
	private VehiculeService vehiculeService;

	@Autowired
	private ParticipantService participantService;

   @Autowired
    private ReportService reportService;
   
	@GetMapping
	public String listerMissions(Model model) {
		model.addAttribute("missions", missionService.listerMissions());
		return "mission/index";
	}

	@GetMapping("/nouveau")
	public String afficherFormulaireCreation(Model model) {
		model.addAttribute("mission", new Mission());
		model.addAttribute("conducteurs", conducteurService.listerConducteurs());
		model.addAttribute("vehicules", vehiculeService.listerVehicules());
		model.addAttribute("participants", participantService.listerParticipants());
		return "mission/create";
	}

	@PostMapping
	public String creerMission(@ModelAttribute Mission mission, Model model) {

		try {
			missionService.creerMission(mission);
		} catch (IllegalArgumentException e) {
			model.addAttribute("errorMessage", e.getMessage());
			model.addAttribute("conducteurs", conducteurService.listerConducteurs());
			model.addAttribute("vehicules", vehiculeService.listerVehicules());
			model.addAttribute("participants", participantService.listerParticipants());
			return "mission/create";
		}
		return "redirect:/missions";
	}

	@GetMapping("/{id}/modifier")
	public String afficherFormulaireModification(@PathVariable Long id, Model model) {
		Mission mission = missionService.trouverMissionParId(id);
		if (mission != null) {
			model.addAttribute("mission", mission);
			model.addAttribute("conducteurs", conducteurService.listerConducteurs());
			model.addAttribute("vehicules", vehiculeService.listerVehicules());
			model.addAttribute("participants", participantService.listerParticipants());
			return "mission/edit";
		} else {
			return "redirect:/missions";
		}
	}

	@PostMapping("/{id}/modifier")
	public String mettreAJourMission(@PathVariable Long id, @ModelAttribute Mission mission) {
		missionService.mettreAJourMission(id, mission);
		return "redirect:/missions";
	}

	@GetMapping("/{id}/supprimer")
	public String supprimerMission(@PathVariable Long id) {
		missionService.supprimerMission(id);
		return "redirect:/missions";
	}

	@GetMapping("/recherche")
	public String afficherFormulaireRecherche() {
		return "mission/form_search";
	}
	
	 @PostMapping("/recherche")
	    public String rechercherMissionsParPeriode(@RequestParam("dateDebut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
	                                               @RequestParam("dateFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin,
	                                               Model model) {
	        List<Mission> missions = missionService.trouverMissionsParPeriode(dateDebut, dateFin);
	        model.addAttribute("missions", missions);
	        model.addAttribute("dateDebut", dateDebut);
	        model.addAttribute("dateFin", dateFin);
	        return "mission/list_mission";
	    }

	    @GetMapping("/exporte/excel")
	    public ResponseEntity<?> exportConducteursEnMissionToExcel(@RequestParam("dateDebut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
	                                                               @RequestParam("dateFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
	        ByteArrayInputStream in;
	        try {
	            in = reportService.exportConducteursEnMissionToExcel(dateDebut, dateFin);
	        } catch (IOException e) {
	            return ResponseEntity.status(500).body("Error generating Excel report");
	        }

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "attachment; filename=conducteurs_en_mission.xlsx");

	        return ResponseEntity.ok()
	                .headers(headers)
	                .body(new InputStreamResource(in));
	    }

	    @GetMapping("/exporte/pdf")
	    public ResponseEntity<?> exportConducteursEnMissionToPDF(@RequestParam("dateDebut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
	                                                             @RequestParam("dateFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin) {
	        ByteArrayInputStream in = reportService.exportConducteursEnMissionToPDF(dateDebut, dateFin);

	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Content-Disposition", "attachment; filename=conducteurs_en_mission.pdf");

	        return ResponseEntity.ok()
	                .headers(headers)
	                .contentType(MediaType.APPLICATION_PDF)
	                .body(new InputStreamResource(in));
	    }

	/*
	 * @PostMapping("/recherche") public String
	 * rechercherMissionsParPeriode(@RequestParam("dateDebut") @DateTimeFormat(iso =
	 * DateTimeFormat.ISO.DATE) LocalDate dateDebut,
	 * 
	 * @RequestParam("dateFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	 * LocalDate dateFin, Model model) {
	 * 
	 * 
	 * List<Mission> missions = missionService.trouverMissionsParPeriode(dateDebut,
	 * dateFin); model.addAttribute("missions", missions); return
	 * "mission/list_mission"; }
	 * 
	 * @GetMapping("/export/excel") public void
	 * exporterMissionsExcel(@RequestParam("dateDebut") @DateTimeFormat(iso =
	 * DateTimeFormat.ISO.DATE) LocalDate dateDebut,
	 * 
	 * @RequestParam("dateFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	 * LocalDate dateFin, HttpServletResponse response) throws IOException {
	 * DateTimeFormatter formatter =
	 * DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); LocalDate dateDebutParsed
	 * = LocalDate.parse(dateDebut); LocalDate dateFinParsed =
	 * LocalDate.parse(dateDebut);
	 * 
	 * List<Mission> missions = missionService.trouverMissionsParPeriode(dateDebut
	 * ,dateDebut ); excelExportService.exportMissionsToExcel(missions, response); }
	 * 
	 * @GetMapping("/export/pdf") public void
	 * exporterMissionsPDF(@RequestParam("dateDebut") String dateDebut,
	 * 
	 * @RequestParam("dateFin") String dateFin, HttpServletResponse response) throws
	 * IOException { DateTimeFormatter formatter =
	 * DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"); LocalDate dateDebutParsed
	 * = LocalDate.parse(dateDebut, formatter); LocalDate dateDebutParsed =
	 * LocalDate.parse(dateDebut); LocalDate dateFinParsed =
	 * LocalDate.parse(dateFin);
	 * 
	 * List<Mission> missions =
	 * missionService.trouverMissionsParPeriode(dateDebutParsed, dateFinParsed);
	 * pdfExportService.exportMissionsToPDF(missions, response); }
	 */

}
