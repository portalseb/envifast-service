package com.bb.envifastservice.adapter.in.web;
import com.bb.envifastservice.adapter.out.persistence.repos.UserRepository;
import com.bb.envifastservice.algo.Usuario;
import com.bb.envifastservice.application.port.in.*;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;
import java.util.Optional;

@WebAdapter
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {
    private final InsertUserService insertUserService;
    private final UpdateUserService updateUserService;
    private final DeleteUserService deleteUserService;
    private final SearchUserLoginService searchUserLoginService;
    private final FindUserByInputService findUserByInputService;
    @PostMapping(value = "/insert")
    public Usuario insertarUsuario(@RequestBody Usuario usuario){

        return insertUserService.insertUser(usuario);
    }

    @PutMapping(value = "/update")
    public Usuario actualizarUsuario(@RequestBody Usuario usuario){
        return updateUserService.updateUser(usuario);
    }

    @DeleteMapping(value = "/delete")
    public int eliminarUsuario(@RequestParam(name = "idUsuario") Integer idUsuario){
        return deleteUserService.deleteUser(idUsuario);
    }

    @GetMapping(value = "/{username} {password}")
    public Usuario buscarUsuarioLogin(@RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        return searchUserLoginService.searchUserLogin(username, password);
    }
    @GetMapping(value = "/lookup")
    public List<Usuario> lookupUsersWithParams(Optional<String> input){
        if (input.isPresent()){
            return findUserByInputService.findUserByInput(input.get());}
        else{
            return findUserByInputService.findUserByInput("");}
    }


}
