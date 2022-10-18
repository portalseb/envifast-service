package com.bb.envifastservice.adapter.out.persistence.repos;


import com.bb.envifastservice.models.AirportsModel;
import com.bb.envifastservice.models.DateTimeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DateTimeRepository extends JpaRepository<DateTimeModel, Long> {
    @Query("SELECT a from DateTimeModel a where a.id = :id and a.active = :active")
    DateTimeModel findByIdOfDateTimeActive(@Param("id") Long id, @Param("active") int active);

}
