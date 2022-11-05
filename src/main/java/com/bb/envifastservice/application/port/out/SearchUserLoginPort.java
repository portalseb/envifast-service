package com.bb.envifastservice.application.port.out;

import com.bb.envifastservice.algo.Usuario;

public interface SearchUserLoginPort {
    Usuario searchUserLogin(String username, String password);
}
