package com.bb.envifastservice.adapter.out.persistence.repos;

import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.models.OrderModel;
import com.bb.envifastservice.models.PackageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

//TODO: FILTRO PARA FECHA Y CANTIDADES!!!
public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    @Query("SELECT o from OrderModel  o WHERE o.codigo like %:campos% or lower(o.destino) like lower(concat('%', :campos, '%')) or lower(o.origen) like lower(concat('%', :campos, '%')) or lower(o.emisorApellidoM) like lower(concat('%', :campos, '%'))or lower(o.emisorApellidoP) like lower(concat('%', :campos, '%'))" +
            "or lower(o.emisorNombres) like lower(concat('%', :campos, '%')) or lower(o.destinatarioApellidoM) like lower(concat('%', :campos, '%')) or lower(o.destinatarioApellidoP) like lower(concat('%', :campos, '%')) or lower(o.destinatarioNombres) like lower(concat('%', :campos, '%')) and o.forSim = :forSim" )
    List<OrderModel> findAllByFieldsLikeAndActive(@Param("campos") String campos, @Param("forSim")Integer forSim);

    @Query("Select o from OrderModel o WHERE o.emisorDocumentoNumero like %:DocNo% or o.destinatarioDocumentoNumero like %:DocNo% and o.token = :token and o.active=1")
    Optional<OrderModel> queryOrdersWithDocNoandToken(@Param("DocNo")String docNo, @Param("token")String token);
    //OrderModel insertOrder(@Param("envio") OrderModel envio);
    @Query("SELECT p from OrderModel  p WHERE p.id =:id and p.active=:active")
    OrderModel findByIdOfOrder(@Param("id") Long id, @Param("active") int active);

    @Query("SELECT o from OrderModel  o WHERE o.active =:active and o.planned =:planned and o.forSim =:forSim" )
    List<OrderModel> findAllByPlanified(@Param("planned") int planned, @Param("forSim")int forSim, @Param("active") int active);
}
