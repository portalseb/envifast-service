package com.bb.envifastservice.application.port.in;

import com.bb.envifastservice.algo.Usuario;

public interface SearchUserLoginService {
    Usuario searchUserLogin(String username, String password);
}
