package com.bb.envifastservice.adapter.out.persistence.repos;

import com.bb.envifastservice.models.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {

    @Query("SELECT o from OrderModel  o WHERE o.codigo like %:campos% or lower(o.destino) like lower(concat('%', :campos, '%')) or lower(o.origen) like lower(concat('%', :campos, '%')) or lower(o.emisorApellidoM) like lower(concat('%', :campos, '%'))or lower(o.emisorApellidoP) like lower(concat('%', :campos, '%'))" +
            "or lower(o.emisorNombres) like lower(concat('%', :campos, '%')) or lower(o.destinatarioApellidoM) like lower(concat('%', :campos, '%')) or lower(o.destinatarioApellidoP) like lower(concat('%', :campos, '%')) or lower(o.destinatarioNombres) like lower(concat('%', :campos, '%')) " )
    List<OrderModel> findAllByFieldsLikeAndActive(@Param("campos") String campos);
}
