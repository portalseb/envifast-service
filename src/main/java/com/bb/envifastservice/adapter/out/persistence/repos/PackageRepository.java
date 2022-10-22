package com.bb.envifastservice.adapter.out.persistence.repos;


import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.algo.Paquete;
import com.bb.envifastservice.models.FlightModel;
import com.bb.envifastservice.models.PackageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PackageRepository  extends JpaRepository<PackageModel, String> {
    @Query("SELECT p FROM PackageModel p WHERE  p.id like %:campos% or lower(p.destino) like lower(concat('%', :campos, '%')) or lower(p.origen) like lower(concat('%', :campos, '%'))")
    List<PackageModel> findAllByFieldsLikeAndActive(@Param("campos") String campos);
    PackageModel findByIdAndActive(Long id, int active);

    @Query("SELECT p from PackageModel  p WHERE p.id =:id and p.active=:active")
    PackageModel findByIdOfPackage(@Param("id") String id, @Param("active") int active);

    //PackageModel insertPackage(@Param("paquete") PackageModel paquete);
    @Query("SELECT p.route from PackageModel  p WHERE p.id =:id and p.active=:active")
    List<FlightModel> findFlightsInPackageRouteAndActive(@Param("id") String id, @Param("active") int active);
}
