package com.bb.envifastservice.adapter.out.persistence.repos;

import com.bb.envifastservice.models.SimulationModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SimulationRepository extends JpaRepository<SimulationModel, Long> {
}
