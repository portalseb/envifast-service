package com.bb.envifastservice.application.port;

import com.bb.envifastservice.algo.Rol;
import com.bb.envifastservice.application.port.in.GetAllRolesService;
import com.bb.envifastservice.application.port.out.GetAllRolesPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class GetAllRolesUseCase implements GetAllRolesService {
    private final GetAllRolesPort getAllRolesPort;
    @Override
    public List<Rol> getAllRoles() {
        return getAllRolesPort.getAllRoles();
    }
}
