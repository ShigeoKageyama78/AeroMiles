package com.aeromiles.config;

import com.aeromiles.model.LoyaltyStatus;
import com.aeromiles.model.Program;
import com.aeromiles.repository.LoyaltyStatusRepository;
import com.aeromiles.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private ProgramRepository programRepository;

    @Autowired
    private LoyaltyStatusRepository loyaltyStatusRepository;

    @Override
    public void run(String... args) {
        // on insère les programmes de fidélité seulement s'ils n'existent pas déjà
        insertProgram("Air France-KLM Flying Blue", "Air France", "Programme SkyTeam");
        insertProgram("Lufthansa Miles & More", "Lufthansa", "Programme Star Alliance");
        insertProgram("Emirates Skywards", "Emirates", "Programme Emirates");
        insertProgram("American Airlines AAdvantage", "American Airlines", "Programme OneWorld");

        System.out.println("Données initialisées");
    }

    private void insertProgram(String name, String airline, String description) {
        if (!programRepository.existsByName(name)) {
            Program p = new Program(name, airline, 1.0, description);
            Program saved = programRepository.save(p);

            LoyaltyStatus ls = new LoyaltyStatus(saved.getId());
            loyaltyStatusRepository.save(ls);
        }
    }
}
