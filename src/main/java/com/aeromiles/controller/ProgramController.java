package com.aeromiles.controller;

import com.aeromiles.model.Program;
import com.aeromiles.model.LoyaltyStatus;
import com.aeromiles.repository.FlightRepository;
import com.aeromiles.repository.LoyaltyStatusRepository;
import com.aeromiles.repository.ProgramRepository;
import com.aeromiles.service.MilesCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/programs")
public class ProgramController {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private LoyaltyStatusRepository loyaltyStatusRepository;

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private MilesCalculatorService milesCalculator;

    @GetMapping
    public List<Map<String, Object>> getAllPrograms() {
        List<Map<String, Object>> result = new ArrayList<>();

        for (Program p : programRepository.findAll()) {
            var loyalty = loyaltyStatusRepository.findByProgramId(p.getId());

            Map<String, Object> data = new HashMap<>();
            data.put("id", p.getId());
            data.put("name", p.getName());
            data.put("airline", p.getAirline());
            data.put("description", p.getDescription());
            data.put("status", loyalty.map(LoyaltyStatus::getCurrentStatus).orElse("Member"));
            data.put("milesYearTotal", loyalty.map(LoyaltyStatus::getMilesThisYear).orElse(0));
            data.put("multiplier", loyalty.map(LoyaltyStatus::getBonusMultiplier).orElse(1.0));
            result.add(data);
        }

        return result;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getProgramById(@PathVariable Long id) {
        return programRepository.findById(id).map(p -> {
            var loyalty = loyaltyStatusRepository.findByProgramId(p.getId());
            var flights = flightRepository.findByProgramIdOrderByDateDesc(p.getId());

            int totalMiles = flights.stream().mapToInt(f -> f.getMilesEarned() != null ? f.getMilesEarned() : 0).sum();
            double totalSpent = flights.stream().mapToDouble(f -> f.getTicketPrice() != null ? f.getTicketPrice() : 0).sum();

            Map<String, Object> stats = new HashMap<>();
            stats.put("totalMiles", totalMiles);
            stats.put("totalSpent", totalSpent);
            stats.put("flightCount", flights.size());

            Map<String, Object> data = new HashMap<>();
            data.put("id", p.getId());
            data.put("name", p.getName());
            data.put("airline", p.getAirline());
            data.put("description", p.getDescription());
            data.put("status", loyalty.map(LoyaltyStatus::getCurrentStatus).orElse("Member"));
            data.put("milesYearTotal", loyalty.map(LoyaltyStatus::getMilesThisYear).orElse(0));
            data.put("flights", flights);
            data.put("stats", stats);

            if (totalMiles > 0) {
                data.put("statusTier", milesCalculator.getStatusTier(totalMiles));
            }

            return ResponseEntity.ok(data);
        }).orElse(ResponseEntity.notFound().build());
    }
}
