package com.aeromiles.model;

import jakarta.persistence.*;

@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long programId;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String departureAirport;

    @Column(nullable = false)
    private String arrivalAirport;

    @Column(nullable = false)
    private Integer distance;

    // "class" est un mot réservé en Java donc on utilise classType
    @Column(name = "classType", nullable = false)
    private String classType;

    @Column(nullable = false)
    private Double ticketPrice;

    @Column(nullable = false)
    private String airline;

    private Integer milesEarned;

    private String notes;

    private String passengerName;

    // constructeur vide obligatoire pour JPA
    public Flight() {}

    // getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getProgramId() { return programId; }
    public void setProgramId(Long programId) { this.programId = programId; }

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getDepartureAirport() { return departureAirport; }
    public void setDepartureAirport(String departureAirport) { this.departureAirport = departureAirport; }

    public String getArrivalAirport() { return arrivalAirport; }
    public void setArrivalAirport(String arrivalAirport) { this.arrivalAirport = arrivalAirport; }

    public Integer getDistance() { return distance; }
    public void setDistance(Integer distance) { this.distance = distance; }

    public String getClassType() { return classType; }
    public void setClassType(String classType) { this.classType = classType; }

    public Double getTicketPrice() { return ticketPrice; }
    public void setTicketPrice(Double ticketPrice) { this.ticketPrice = ticketPrice; }

    public String getAirline() { return airline; }
    public void setAirline(String airline) { this.airline = airline; }

    public Integer getMilesEarned() { return milesEarned; }
    public void setMilesEarned(Integer milesEarned) { this.milesEarned = milesEarned; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getPassengerName() { return passengerName; }
    public void setPassengerName(String passengerName) { this.passengerName = passengerName; }
}
