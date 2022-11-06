package com.bb.envifastservice.application.port;
import com.bb.envifastservice.algo.Usuario;
import com.bb.envifastservice.application.port.in.UpdateUserService;
import com.bb.envifastservice.application.port.out.UpdateUserPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateUserUseCase implements UpdateUserService {
    private final UpdateUserPort updateUserPort;

    @Override
    public Usuario updateUser(Usuario usuario){return updateUserPort.updateUser(usuario);}
}
