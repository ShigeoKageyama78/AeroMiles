package com.aeromiles.controller;

import com.aeromiles.model.Flight;
import com.aeromiles.model.LoyaltyStatus;
import com.aeromiles.model.Program;
import com.aeromiles.repository.FlightRepository;
import com.aeromiles.repository.LoyaltyStatusRepository;
import com.aeromiles.repository.ProgramRepository;
import com.aeromiles.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private LoyaltyStatusRepository loyaltyStatusRepository;

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/flight/{id}")
    public ResponseEntity<Map<String, Object>> getFlightInvoice(@PathVariable Long id) {
        return flightRepository.findById(id).map(flight -> {
            String programName = programRepository.findById(flight.getProgramId())
                    .map(Program::getName).orElse("Programme inconnu");

            String status = loyaltyStatusRepository.findByProgramId(flight.getProgramId())
                    .map(LoyaltyStatus::getCurrentStatus).orElse("Member");

            Map<String, Object> invoice = invoiceService.generateFlightInvoice(flight, programName, status);
            String html = invoiceService.generateHTMLInvoice(invoice);

            return ResponseEntity.ok(Map.of("invoice", invoice, "html", html));
        }).orElse(ResponseEntity.notFound().build());
    }
}
