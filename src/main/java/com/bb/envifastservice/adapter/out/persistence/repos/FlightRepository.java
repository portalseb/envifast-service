package com.bb.envifastservice.adapter.out.persistence.repos;

import com.bb.envifastservice.models.AirportsModel;
import com.bb.envifastservice.models.FlightModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface FlightRepository extends JpaRepository<FlightModel, Long> {
    Optional<FlightModel> findByIdAndActive(Long id, int active);
    @Query("SELECT a from FlightModel a where a.active = :active and a.departureTime >= :inf " +
            "ORDER BY a.departureTime ASC")
    List<FlightModel> findAllAfterDate(@Param("active") int active,@Param("inf") LocalDateTime inf);

    @Query("SELECT a from FlightModel a where a.active = :active and a.departureTime >= :inf and " +
            "a.departureTime <= :sup and a.forSim = :paraSim ORDER BY a.departureTime ASC")
    List<FlightModel> findAllByActiveRange(@Param("active") int active,@Param("inf") LocalDateTime inf,@Param("sup") LocalDateTime sup, @Param("paraSim")int paraSim);
    @Modifying
    @Query("DELETE from FlightModel a where a.forSim = :paraSim and a.active = :active")
    void deleteByParaSim(@Param("paraSim")int paraSim, @Param("active") int active);

    @Query("SELECT a from FlightModel a where a.id = :idFlight")
    FlightModel findByFlightId(@Param("idFlight") Long idFlight);
    @Query(value = "CALL restore_flight_for_sim(:fechaIni, :fechaFin, :forSim, :active);", nativeQuery = true)
    void restoreFlights(@Param("fechaIni")LocalDateTime fechaIni,@Param("fechaFin") LocalDateTime fechaFin,@Param("forSim") Integer forSim,@Param("active") Integer active);

    //@Query("SELECT a from FlightModel a where a.departureTime = :fechaIni and a.departureAirport.id = :origen and a.arrivalAirport.id =: destino and a.forSim = :forSim and a.active = :active")
    //FlightModel findFlightByDateTimeDepartureArrival(@Param("fechaIni")LocalDateTime fechaIni, @Param("origen")Long origen, @Param("destino")Long destino, @Param("forSim") Integer forSim, @Param("active") int active);
}
