package com.bb.envifastservice.application.port;
import com.bb.envifastservice.application.port.in.DeleteRoleService;
import com.bb.envifastservice.application.port.out.DeleteRolePort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteRoleUseCase implements DeleteRoleService {
    private final DeleteRolePort deleteRolePort;

    @Override
    public int deleteRole(Integer id) {
        return deleteRolePort.deleteRole(id);
    }
}
