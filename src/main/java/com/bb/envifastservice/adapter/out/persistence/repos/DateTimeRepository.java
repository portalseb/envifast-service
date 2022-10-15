package com.bb.envifastservice.adapter.out.persistence.repos;


import com.bb.envifastservice.models.DateTimeModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DateTimeRepository extends JpaRepository<DateTimeModel, Long> {

}
