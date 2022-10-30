package com.bb.envifastservice.adapter.out.persistence.repos;

import com.bb.envifastservice.models.FlightModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<FlightModel, Long> {
    Optional<FlightModel> findByIdAndActive(Long id, int active);
    @Query("SELECT a from FlightModel a where a.active = :active and a.departureTime >= :inf and " +
            "a.departureTime <= :sup and a.forSim = :paraSim ORDER BY a.departureTime ASC")
    List<FlightModel> findAllByActiveRange(@Param("active") int active,@Param("inf") LocalDateTime inf,@Param("sup") LocalDateTime sup, @Param("paraSim")int paraSim);
    @Query("SELECT a from FlightModel a where a.active = :active and a.departureTime >= :inf " +
            "ORDER BY a.departureTime ASC")
    List<FlightModel> findAllAfterDate(@Param("active") int active,@Param("inf") LocalDateTime inf);

    @Modifying
    @Query("DELETE from FlightModel a where a.forSim = :paraSim and a.active = :active")
    void deleteByParaSim(@Param("paraSim")int paraSim, @Param("active") int active);

    @Query("SELECT a from FlightModel a where a.id = :idFlight")
    FlightModel findByFlightId(@Param("idFlight") Long idFlight);

}
