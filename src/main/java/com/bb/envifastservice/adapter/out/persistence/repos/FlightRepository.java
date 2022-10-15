package com.bb.envifastservice.adapter.out.persistence.repos;

import com.bb.envifastservice.models.FlightModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<FlightModel, Long> {
    Optional<FlightModel> findByIdAndActive(Long id, int active);
}
