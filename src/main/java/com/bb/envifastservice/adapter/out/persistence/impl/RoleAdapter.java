package com.bb.envifastservice.adapter.out.persistence.impl;

import com.bb.envifastservice.adapter.out.persistence.repos.RoleRepository;
import com.bb.envifastservice.algo.Rol;
import com.bb.envifastservice.application.port.out.DeleteRolePort;
import com.bb.envifastservice.application.port.out.GetAllRolesPort;
import com.bb.envifastservice.application.port.out.InsertRolePort;
import com.bb.envifastservice.application.port.out.UpdateRolePort;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.RoleModel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class RoleAdapter implements GetAllRolesPort, InsertRolePort, UpdateRolePort, DeleteRolePort {
    private final RoleRepository roleRepository;
    @Override
    public List<Rol> getAllRoles() {
        var roles = new ArrayList<Rol>();
        var bdRoles =roleRepository.findAll();
        for (RoleModel role: bdRoles
             ) {
            var rol = new Rol();
            rol.setId(role.getId());
            rol.setNombreRol(role.getName());
        }
        return roles;
    }

    @Override
    public Rol insertRole(Rol rol) {
        var rolNuevo = new RoleModel();
        rolNuevo.setName(rol.getNombreRol());
        rolNuevo.setActive(1);
        roleRepository.save(rolNuevo);
        rol.setId(rolNuevo.getId());
        rol.setActivo(1);
        return rol;
    }

    @Override
    public Rol updateRole(Rol rol) {
        var rolBD = roleRepository.findByIdRole(rol.getId());
        rolBD.setName(rol.getNombreRol());
        rolBD.setActive(rol.getActivo());
        roleRepository.save(rolBD);
        return rol;
    }
    @Override
    public int deleteRole(Integer id) {
        var rolBD = roleRepository.findByIdActive(id.longValue(),1);
        if(rolBD==null) return 0;
        rolBD.setActive(0);
        //tambien se deletearia a los usuarios que tengan este rol??
        roleRepository.save(rolBD);
        return 1;
    }
}
