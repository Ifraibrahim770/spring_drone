package com.ibrahim.drone.Services;

import com.ibrahim.drone.Entities.Drone;
import com.ibrahim.drone.Entities.Medication;
import com.ibrahim.drone.Exceptions.ResourceNotFoundException;
import com.ibrahim.drone.Repositories.DroneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicationService {

    @Autowired
    private DroneRepository droneRepository;

    public List<Medication> getLoadedMedication(Long id) {
        // Find the drone by ID
        Drone drone = droneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Drone not found"));

        // Return the list of medications for the drone
        return drone.getLoadedMedications();
    }
}
