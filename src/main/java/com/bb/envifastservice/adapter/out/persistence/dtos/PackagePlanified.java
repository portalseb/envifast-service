package com.bb.envifastservice.adapter.out.persistence.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PackagePlanified {
    String codEnvio;
    String codPaquete;
    LocalDateTime fecha;
}
