package com.bb.envifastservice.application.port;

import com.bb.envifastservice.algo.Envio;
import com.bb.envifastservice.algo.Paquete;
import com.bb.envifastservice.application.port.in.ListPackagesService;
import com.bb.envifastservice.application.port.out.ListPackagesPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ListPackagesUseCase implements ListPackagesService {
    private final ListPackagesPort listPackagesPort;
    @Override
    public List<Envio> listByFields(String input) {
        return listPackagesPort.listByFields(input);
    }
}
