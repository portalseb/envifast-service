package com.bb.envifastservice.application.port;
import com.bb.envifastservice.algo.Usuario;
import com.bb.envifastservice.application.port.in.InsertUserService;
import com.bb.envifastservice.application.port.out.InsertUserPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class InsertUserUseCase implements InsertUserService {
    private final InsertUserPort insertUserPort;

    @Override
    public Usuario insertUser(Usuario usuario){return insertUserPort.insertUser(usuario);}
}
