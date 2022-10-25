package com.bb.envifastservice.adapter.out.persistence.repos;

import com.bb.envifastservice.models.AirportsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AirportRepository extends JpaRepository<AirportsModel, Long> {
    AirportsModel  findByCityNameAndActive(String id, int active);

    @Query("SELECT a from AirportsModel a where a.id = :id and a.active = :active")
    AirportsModel findByIdActive(Long id, int active);

    List<AirportsModel> findAllByActive(int active);
    @Query("SELECT a from AirportsModel a where a.airportCode = :cityName and a.active = :active")
    AirportsModel findByCityShortNameAndActive(@Param("cityName") String cityShortname,@Param("active") int active);
}
