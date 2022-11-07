package com.bb.envifastservice.adapter.out.persistence.dtos;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class FlightMap {
    Integer id;
    Integer idAeropuertoOrigen;
    Integer idAeropuertoDestino;
    LocalDateTime horaSalida;
    LocalDateTime horaLLegada;
    int duracion;
    int cantPaquetes;
    int cantMax;
}
