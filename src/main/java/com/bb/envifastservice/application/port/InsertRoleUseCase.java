package com.bb.envifastservice.application.port;
import com.bb.envifastservice.algo.Rol;
import com.bb.envifastservice.application.port.in.InsertRoleService;
import com.bb.envifastservice.application.port.out.InsertRolePort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class InsertRoleUseCase implements InsertRoleService {
    private final InsertRolePort insertRolePort;

    @Override
    public Rol insertRole(Rol rol) {
        return insertRolePort.insertRole(rol);
    }
}
