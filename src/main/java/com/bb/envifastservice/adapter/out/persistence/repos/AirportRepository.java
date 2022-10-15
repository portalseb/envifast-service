package com.bb.envifastservice.adapter.out.persistence.repos;

import com.bb.envifastservice.models.AirportsModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportRepository extends JpaRepository<AirportsModel, Long> {
    AirportsModel  findByCityNameAndActive(String id, int active);
    List<AirportsModel> findAllByActive(int active);
}
