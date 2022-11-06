package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.algo.Usuario;

import java.util.List;

public interface FindUserByInputService {
    List<Usuario> findUserByInput(String input);
}
