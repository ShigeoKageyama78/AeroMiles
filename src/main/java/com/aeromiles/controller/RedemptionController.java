package com.aeromiles.controller;

import com.aeromiles.model.Redemption;
import com.aeromiles.repository.RedemptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/redemptions")
public class RedemptionController {

    @Autowired
    private RedemptionRepository redemptionRepository;

    @PostMapping
    public ResponseEntity<Map<String, Object>> addRedemption(@RequestBody Map<String, Object> body) {
        try {
            Redemption r = new Redemption();
            r.setProgramId(Long.valueOf(body.get("programId").toString()));
            r.setDate(body.get("date").toString());
            r.setMilesUsed(Integer.valueOf(body.get("milesUsed").toString()));
            r.setTicketPrice(body.get("ticketPrice") != null ? Double.valueOf(body.get("ticketPrice").toString()) : null);
            r.setDescription(body.get("description") != null ? body.get("description").toString() : null);

            Redemption saved = redemptionRepository.save(r);
            return ResponseEntity.ok(Map.of("id", saved.getId(), "message", "Rédemption enregistrée"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping
    public List<Redemption> getRedemptions(@RequestParam(required = false) Long programId) {
        if (programId != null) {
            return redemptionRepository.findByProgramIdOrderByDateDesc(programId);
        }
        return redemptionRepository.findAllByOrderByDateDesc();
    }
}
