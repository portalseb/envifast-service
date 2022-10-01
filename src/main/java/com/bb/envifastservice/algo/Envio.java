package com.bb.envifastservice.algo;

import java.time.LocalDate;
import java.util.ArrayList;

public class Envio {
    private Integer id;
    private Integer codigo;
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

    private ArrayList<Paquete> paquetes;
    private Integer cantidadPaquetes;

    private Aeropuerto origen;
    private Aeropuerto destino;

    private LocalDate fechaEnvio;
    private double tiempoTotal;

    private String token;
}
