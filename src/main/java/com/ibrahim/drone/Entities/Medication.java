package com.ibrahim.drone.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Medication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private int weight;

    @Column(name = "code")
    private String code;

    @Column(name = "image")
    private String image;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "drone_id", referencedColumnName = "id")
    private Drone drone;

    // Getters and setters for the properties

}