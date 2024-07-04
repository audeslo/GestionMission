package com.eneam.gestionmission.controller.api;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eneam.gestionmission.model.Mission;
import com.eneam.gestionmission.service.MissionService;

@RestController
@RequestMapping("/api/missions")
public class MissionApiController {
	
	@Autowired
    private MissionService missionService;

    @GetMapping("/periode")
    public List<Mission> getMissionsByPeriod(
            @RequestParam("dateDebut") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dateDebut,
            @RequestParam("dateFin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDate dateFin) {
        return missionService.trouverMissionsParPeriode(dateDebut, dateFin);
    }

}
