package com.ibrahim.drone.Entities;

import com.ibrahim.drone.Entities.Medication;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "drones")
@Getter
@Setter
@NoArgsConstructor


public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "model")
    @Enumerated(EnumType.STRING)
    private Model model;

    @Column(name = "weight_limit")
    private int weightLimit;

    @Column(name = "battery_capacity")
    private int batteryCapacity;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "drone")
    private List<Medication> loadedMedications;

    public enum Model {
        LIGHTWEIGHT, MIDDLEWEIGHT, CRUISERWEIGHT, HEAVYWEIGHT
    }

    public enum State {
        IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING
    }

    // Getters and setters for the properties

    public Drone(String serialNumber, Model model, int weightLimit, int batteryCapacity, State state, List<Medication> loadedMedications) {
        this.serialNumber = serialNumber;
        this.model = model;
        this.weightLimit = weightLimit;
        this.batteryCapacity = batteryCapacity;
        this.state = state;
        this.loadedMedications = loadedMedications;
    }


}
