package com.bb.envifastservice.adapter.out.persistence.repos;


import com.bb.envifastservice.models.AirportsModel;
import com.bb.envifastservice.models.DateTimeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public interface DateTimeRepository extends JpaRepository<DateTimeModel, Long> {
    @Query("SELECT a from DateTimeModel a where a.id = :id and a.active = :active")
    DateTimeModel findByIdOfDateTimeActive(@Param("id") Long id, @Param("active") int active);

    @Query("SELECT a from DateTimeModel a where a.dateTime = :dateTime and a.active = :active")
    List<DateTimeModel> findByFechaHoraActive(@Param("dateTime") LocalDateTime dateTime, @Param("active") int active);
    @Query("SELECT a from DateTimeModel a where a.dateTime >= :inf and a.dateTime <= :sup and a.active = :active ORDER BY a.dateTime ASC")
    List<DateTimeModel> findDateTimesActiveRange(@Param("inf") LocalDateTime inf, @Param("sup") LocalDateTime sup, @Param("active") int active);


}
