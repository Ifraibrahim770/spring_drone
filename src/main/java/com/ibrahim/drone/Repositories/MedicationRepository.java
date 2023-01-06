package com.ibrahim.drone.Repositories;

import com.ibrahim.drone.Entities.Medication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication,Long> {
}
