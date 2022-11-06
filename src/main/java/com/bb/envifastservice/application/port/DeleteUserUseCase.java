package com.bb.envifastservice.application.port;
import com.bb.envifastservice.application.port.in.DeleteUserService;
import com.bb.envifastservice.application.port.out.DeleteUserPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteUserUseCase implements DeleteUserService {
    private final DeleteUserPort deleteUserPort;

    @Override
    public int deleteUser(Integer id) {
        return deleteUserPort.deleteUser(id);
    }
}
