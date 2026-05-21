package com.aeromiles.repository;

import com.aeromiles.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {

    List<Flight> findByProgramIdOrderByDateDesc(Long programId);

    List<Flight> findAllByOrderByDateDesc();

    // top 10 des destinations les plus visitées
    @Query("SELECT f.arrivalAirport, COUNT(f) as freq FROM Flight f GROUP BY f.arrivalAirport ORDER BY freq DESC")
    List<Object[]> findTopDestinations();

    // compagnie la plus utilisée
    @Query("SELECT f.airline, COUNT(f) as cnt FROM Flight f GROUP BY f.airline ORDER BY cnt DESC")
    List<Object[]> findTopAirlines();
}
