package com.bb.envifastservice.adapter.out.persistence.repos;

import com.bb.envifastservice.models.AirportsCapacityModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AirportCapacityRepository extends JpaRepository<AirportsCapacityModel, Long> {
    @Query("SELECT p from AirportsCapacityModel  p WHERE p.id =:id and p.active=:active")
    AirportsCapacityModel findByIdCapacity(@Param("id") Long id, @Param("active") int active);

    @Query("SELECT p from AirportsCapacityModel  p WHERE p.id =:id and p.active=:active")
    Long findByIdAirportActive(@Param("id") Long id, @Param("active") int active);

    @Modifying
    @Query("DELETE from AirportsCapacityModel a where a.forSim = :paraSim and a.active = :active")
    void deleteByForSim(@Param("paraSim")int paraSim, @Param("active") int active);

}
