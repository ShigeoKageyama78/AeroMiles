package com.aeromiles.repository;

import com.aeromiles.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program, Long> {
    boolean existsByName(String name);
}
