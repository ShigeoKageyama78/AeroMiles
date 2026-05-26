package com.aeromiles.model;

import jakarta.persistence.*;

@Entity
@Table(name = "programs")
public class Program {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String airline;

    private Double baseMultiplier = 1.0;

    private String description;

    // constructeurs
    public Program() {}

    public Program(String name, String airline, Double baseMultiplier, String description) {
        this.name = name;
        this.airline = airline;
        this.baseMultiplier = baseMultiplier;
        this.description = description;
    }

    // getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }

    public Double getBaseMultiplier() { return baseMultiplier; }
    public void setBaseMultiplier(Double baseMultiplier) { this.baseMultiplier = baseMultiplier; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
