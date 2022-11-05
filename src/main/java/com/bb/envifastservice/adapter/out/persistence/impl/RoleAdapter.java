package com.bb.envifastservice.adapter.out.persistence.impl;

import com.bb.envifastservice.adapter.out.persistence.repos.RoleRepository;
import com.bb.envifastservice.algo.Rol;
import com.bb.envifastservice.application.port.out.GetAllRolesPort;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.RoleModel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class RoleAdapter implements GetAllRolesPort {
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
}
