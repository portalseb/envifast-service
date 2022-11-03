package com.bb.envifastservice.adapter.in.web;
import com.bb.envifastservice.algo.Usuario;
import com.bb.envifastservice.application.port.in.InsertUserService;
import com.bb.envifastservice.application.port.in.SearchUserLoginService;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
    private final InsertUserService insertUserService;
    private final SearchUserLoginService searchUserLoginService;

    @PostMapping(value = "/insert")
    public Usuario insertarUsuario(@RequestBody Usuario usuario){return insertUserService.insertUser(usuario);}

    @GetMapping(value = "/{username} {password}")
    public Usuario buscarUsuarioLogin(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        return searchUserLoginService.searchUserLogin(username, password);
    }


}
