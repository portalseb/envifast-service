package com.bb.envifastservice.application.port.out;

import com.bb.envifastservice.algo.Usuario;

import java.util.List;

public interface FindUserByInputPort {
    List<Usuario> findUserByInput(String input);
}
