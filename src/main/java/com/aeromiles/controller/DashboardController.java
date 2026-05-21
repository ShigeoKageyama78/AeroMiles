package com.aeromiles.controller;

import com.aeromiles.service.DashboardService;
import com.aeromiles.service.MilesCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @Autowired
    private MilesCalculatorService milesCalculator;

    @GetMapping("/stats/dashboard")
    public Map<String, Object> getDashboard() {
        return dashboardService.getDashboardData();
    }

    @GetMapping("/calculate/status-tier/{miles}")
    public Map<String, Object> getStatusTier(@PathVariable int miles) {
        return milesCalculator.getStatusTier(miles);
    }
}
