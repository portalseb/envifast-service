package com.bb.envifastservice.algo;

import java.time.LocalDate;
import java.time.LocalTime;

public class FechaHora {
    private Integer id;
    private LocalDate dia;
    private LocalTime hora;

    public FechaHora() {
    }

    public FechaHora(Integer id, String dia, String hora) {
        this.id = id;
        this.dia = LocalDate.parse(dia);
        this.hora = LocalTime.parse(hora);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getDia() {
        return dia;
    }

    public void setDia(LocalDate dia) {
        this.dia = dia;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }
}
