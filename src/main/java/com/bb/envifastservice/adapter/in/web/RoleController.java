package com.bb.envifastservice.adapter.in.web;


import com.bb.envifastservice.algo.Rol;
import com.bb.envifastservice.application.port.in.GetAllRolesService;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@WebAdapter
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/roles")
public class RoleController {
    private final GetAllRolesService getAllRolesService;
    @GetMapping(value = "")
    public List<Rol> listRoles(){

            return getAllRolesService.getAllRoles();

    }

}

