package com.bb.envifastservice.adapter.out.persistence.repos;

import com.bb.envifastservice.models.AirportsCapacityModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AirportCapacityRepository extends JpaRepository<AirportsCapacityModel, Long> {

}
