package com.ibrahim.drone.Controllers;

import com.ibrahim.drone.Entities.Drone;
import com.ibrahim.drone.Entities.Medication;
import com.ibrahim.drone.Services.DroneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drones")
public class DroneController {

    @Autowired
    private DroneService droneService;

    public DroneController(DroneService mockDroneService) {
        this.droneService = mockDroneService;
    }

    @PostMapping("/register")
    public ResponseEntity<Drone> registerDrone(@RequestBody Drone drone) {
        Drone registeredDrone = droneService.registerDrone(drone);
        return ResponseEntity.ok(registeredDrone);
    }

    @PutMapping("/{id}/load")
    public ResponseEntity<Drone> loadMedication(@PathVariable("id") Long id, @RequestBody List<Medication> medications) {
        // Load the medication onto the drone
        Drone updatedDrone = droneService.loadMedication(id, medications);

        // Return the updated drone
        return ResponseEntity.ok(updatedDrone);
    }
    @GetMapping("/{id}/medication")
    public ResponseEntity<List<Medication>> getLoadedMedication(@PathVariable(value = "id") Long id) {
        List<Medication> medication = droneService.getLoadedMedication(id);
        return ResponseEntity.ok().body(medication);
    }
    @GetMapping("/available")
    public ResponseEntity<List<Drone>> getAvailableDrones() {
        List<Drone> drones = droneService.getAvailableDrones();
        return ResponseEntity.ok(drones);
    }
    @GetMapping("/{id}/battery")
    public ResponseEntity<Integer> getDroneBatteryLevel(@PathVariable Long id) {
        int batteryLevel = droneService.getDroneBatteryLevel(id);
        return ResponseEntity.ok(batteryLevel);
    }
}
