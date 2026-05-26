package com.aeromiles.repository;

import com.aeromiles.model.Redemption;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RedemptionRepository extends JpaRepository<Redemption, Long> {
    List<Redemption> findByProgramIdOrderByDateDesc(Long programId);
    List<Redemption> findAllByOrderByDateDesc();
}
