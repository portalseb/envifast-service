package com.bb.envifastservice.application.port;

import com.bb.envifastservice.algo.Usuario;
import com.bb.envifastservice.application.port.in.FindUserByInputService;
import com.bb.envifastservice.application.port.out.FindUserByInputPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindUserByInputUseCase implements FindUserByInputService {
    private final FindUserByInputPort findUserByInputPort;

    @Override
    public List<Usuario> findUserByInput(String input) {
        return findUserByInputPort.findUserByInput(input);
    }
}
