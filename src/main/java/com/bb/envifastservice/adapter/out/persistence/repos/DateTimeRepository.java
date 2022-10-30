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

    @Query("SELECT a from DateTimeModel a where a.date = :date and a.time = :time and a.active = :active")
    List<DateTimeModel> findByFechaHoraActive(@Param("date") LocalDate date, @Param("time") LocalTime time, @Param("active") int active);
    @Query("SELECT a from DateTimeModel a where a.date >= :inf and a.date <= :sup and a.active = :active ORDER BY a.date ASC, a.time ASC")
    List<DateTimeModel> findDateTimesActiveRange(@Param("inf") LocalDate inf, @Param("sup") LocalDate sup, @Param("active") int active);

}
