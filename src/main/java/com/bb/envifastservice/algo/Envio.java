package com.bb.envifastservice.algo;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

@Data
public class Envio {
    private Long id;
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

    private LinkedList<Paquete> paquetes;
    private Integer cantidadPaquetes;

    private Aeropuerto origen;
    private Aeropuerto destino;

    private LocalDateTime fechaEnvio;
    private double tiempoTotal;

    private String token;
}
