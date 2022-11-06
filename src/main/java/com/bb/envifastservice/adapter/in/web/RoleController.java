package com.bb.envifastservice.adapter.in.web;


import com.bb.envifastservice.algo.Rol;
import com.bb.envifastservice.application.port.in.DeleteRoleService;
import com.bb.envifastservice.application.port.in.GetAllRolesService;
import com.bb.envifastservice.application.port.in.InsertRoleService;
import com.bb.envifastservice.application.port.in.UpdateRoleService;
import com.bb.envifastservice.hexagonal.WebAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@WebAdapter
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/roles")
public class RoleController {
    private final GetAllRolesService getAllRolesService;
    private final InsertRoleService insertRoleService;
    private final UpdateRoleService updateRoleService;
    private final DeleteRoleService deleteRoleService;

    @GetMapping(value = "")
    public List<Rol> listRoles(){

            return getAllRolesService.getAllRoles();

    }

    @PostMapping(value = "/insert")
    public Rol insertarRol(@RequestBody Rol rol){
        return insertRoleService.insertRole(rol);
    }

    @PutMapping(value = "/update")
    public Rol actualizarRol(@RequestBody Rol rol){
        return updateRoleService.updateRole(rol);
    }

    @DeleteMapping(value = "/delete")
    public int eliminarRol(@RequestParam(name = "idRol") Integer idRol){
        return deleteRoleService.deleteRole(idRol);
    }

}

