package com.bb.envifastservice.application.port;
import com.bb.envifastservice.algo.Rol;
import com.bb.envifastservice.application.port.in.UpdateRoleService;
import com.bb.envifastservice.application.port.out.UpdateRolePort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateRoleUseCase implements UpdateRoleService {
    private final UpdateRolePort updateRolePort;

    @Override
    public Rol updateRole(Rol rol) {
        return updateRolePort.updateRole(rol);
    }
}
