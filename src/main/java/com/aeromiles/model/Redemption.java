package com.aeromiles.model;

import jakarta.persistence.*;

@Entity
@Table(name = "redemptions")
public class Redemption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long programId;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private Integer milesUsed;

    private Double ticketPrice;

    private String description;

    public Redemption() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProgramId() { return programId; }
    public void setProgramId(Long programId) { this.programId = programId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public Integer getMilesUsed() { return milesUsed; }
    public void setMilesUsed(Integer milesUsed) { this.milesUsed = milesUsed; }

    public Double getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(Double ticketPrice) { this.ticketPrice = ticketPrice; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
