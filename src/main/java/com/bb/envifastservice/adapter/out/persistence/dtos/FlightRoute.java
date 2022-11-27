package com.bb.envifastservice.adapter.out.persistence.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FlightRoute {
    String ciudadOrigen;
    String ciudadDestino;
    LocalDateTime horaSalida;
    LocalDateTime horaLLegada;
}
