package com.aeromiles.controller;

import com.aeromiles.model.Flight;
import com.aeromiles.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addFlight(@RequestBody Map<String, Object> body) {
        try {
            Flight flight = flightService.addFlight(body);

            Map<String, Object> response = new HashMap<>();
            response.put("id", flight.getId());
            response.put("milesEarned", flight.getMilesEarned());
            response.put("message", "Vol ajouté avec succès");

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public List<Flight> getFlights(@RequestParam(required = false) Long programId) {
        return flightService.getFlights(programId);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteFlight(@PathVariable Long id) {
        try {
            flightService.deleteFlight(id);
            return ResponseEntity.ok(Map.of("message", "Vol supprimé"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
