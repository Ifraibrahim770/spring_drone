package com.ibrahim.drone.Controllers;

import com.ibrahim.drone.Entities.Drone;
import com.ibrahim.drone.Entities.Medication;
import com.ibrahim.drone.Services.DroneService;
import com.ibrahim.drone.Services.MedicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medication")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @GetMapping("drone/{id}")
    public ResponseEntity<List<Medication>> getLoadedMedication(@PathVariable(value = "id") Long id) {
        List<Medication> medication = medicationService.getLoadedMedication(id);
        return ResponseEntity.ok().body(medication);
    }
}
