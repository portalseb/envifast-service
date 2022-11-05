package com.bb.envifastservice.application.port;
import com.bb.envifastservice.algo.Usuario;
import com.bb.envifastservice.application.port.in.SearchUserLoginService;
import com.bb.envifastservice.application.port.out.SearchUserLoginPort;
import com.bb.envifastservice.hexagonal.UseCase;
import lombok.RequiredArgsConstructor;
@UseCase
@RequiredArgsConstructor
public class SearchUserLoginUseCase implements SearchUserLoginService {
    private final SearchUserLoginPort searchUserLoginPort;

    @Override
    public Usuario searchUserLogin(String username, String password){return searchUserLoginPort.searchUserLogin(username,password);}


}
