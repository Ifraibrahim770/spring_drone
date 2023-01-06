package com.ibrahim.drone.Repositories;

import com.ibrahim.drone.Entities.Drone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DroneRepository extends JpaRepository<Drone, Long> {

    Drone findBySerialNumber(String serialNumber);

    List<Drone> findByState(Drone.State state);
}
