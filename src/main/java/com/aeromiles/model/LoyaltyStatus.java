package com.aeromiles.model;

import jakarta.persistence.*;

@Entity
@Table(name = "loyalty_status")
public class LoyaltyStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long programId;

    private String currentStatus = "Member";

    private Integer milesThisYear = 0;

    private Double bonusMultiplier = 1.0;

    public LoyaltyStatus() {}

    public LoyaltyStatus(Long programId) {
        this.programId = programId;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProgramId() { return programId; }
    public void setProgramId(Long programId) { this.programId = programId; }

    public String getCurrentStatus() { return currentStatus; }
    public void setCurrentStatus(String currentStatus) { this.currentStatus = currentStatus; }

    public Integer getMilesThisYear() { return milesThisYear; }
    public void setMilesThisYear(Integer milesThisYear) { this.milesThisYear = milesThisYear; }

    public Double getBonusMultiplier() { return bonusMultiplier; }
    public void setBonusMultiplier(Double bonusMultiplier) { this.bonusMultiplier = bonusMultiplier; }
}
