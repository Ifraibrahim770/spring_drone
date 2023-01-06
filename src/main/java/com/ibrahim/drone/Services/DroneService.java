package com.ibrahim.drone.Services;

import com.ibrahim.drone.Entities.Drone;
import com.ibrahim.drone.Entities.Medication;
import com.ibrahim.drone.Exceptions.ResourceNotFoundException;
import com.ibrahim.drone.Repositories.DroneRepository;
import com.ibrahim.drone.Repositories.MedicationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneService {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private MedicationRepository medicationRepository;

    public Drone registerDrone(Drone drone) {
        return droneRepository.save(drone);
    }
    @Transactional
    public Drone loadMedication(Long id, List<Medication> medications) {
        // Find the drone by ID
        Drone drone = droneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Drone not found"));

        // Check if the drone is in the correct state to be loaded with medication
        if (drone.getState() != Drone.State.IDLE) {
            throw new IllegalStateException("Drone is not in the correct state to be loaded with medication");
        }
        drone.setState(Drone.State.LOADING);
        droneRepository.save(drone);

        // Check if the drone has enough weight capacity to carry the medication
        int totalWeight = medications.stream().mapToInt(Medication::getWeight).sum();
        if (totalWeight > drone.getWeightLimit()) {
            throw new IllegalStateException("Drone does not have enough weight capacity to carry the medication");
        }

        // Check if the drone has enough battery to load the medication
        if (drone.getBatteryCapacity() < 25) {
            throw new IllegalStateException("Drone does not have enough battery to load the medication");
        }

        for (Medication medication : medications) {
            medication.setDrone(drone);
            medicationRepository.save(medication);
        }
        drone.setState(Drone.State.LOADED);
        return droneRepository.save(drone);
    }
    public List<Medication> getLoadedMedication(Long id) {
        // Find the drone by ID
        Drone drone = droneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Drone not found"));

        // Return the list of medications for the drone
        return drone.getLoadedMedications();
    }

    public List<Drone> getAvailableDrones() {
        return droneRepository.findByState(Drone.State.IDLE);
    }

    public int getDroneBatteryLevel(Long id) {
        Drone drone = droneRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Drone not found"));
        return drone.getBatteryCapacity();
    }
}
