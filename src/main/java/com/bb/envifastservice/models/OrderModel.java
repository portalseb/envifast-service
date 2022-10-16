package com.bb.envifastservice.models;

import com.sun.xml.bind.v2.runtime.reflect.Lister;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "Envio")
public class OrderModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    private String codigo;
    private String emisorNombres;
    private String emisorApellidoP;
    private String emisorApellidoM;
    private String emisorDocumentoTipo;
    private String emisorDocumentoNumero;
    private String emisorCorreo;
    private String emisorTelefonoNumero;

    private String destinatarioNombres;
    private String destinatarioApellidoP;
    private String destinatarioApellidoM;
    private String destinatarioDocumentoTipo;
    private String destinatarioDocumentoNumero;
    private String destinatarioCorreo;
    private String destinatarioTelefonoNumero;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="order_id")
    private List<PackageModel> packs = new ArrayList<>();
    private Integer cantidad;
    private LocalDateTime fechaEnvio;
    private double tiempoTotal;
    @Column(name = "secretToken")
    private String token;
    private String origen;
    private String destino;
    private int active;
}
