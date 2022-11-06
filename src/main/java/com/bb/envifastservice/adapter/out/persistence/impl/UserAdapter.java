package com.bb.envifastservice.adapter.out.persistence.impl;
import com.bb.envifastservice.adapter.out.persistence.repos.AirportRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.RoleRepository;
import com.bb.envifastservice.adapter.out.persistence.repos.UserRepository;
import com.bb.envifastservice.algo.*;
import com.bb.envifastservice.application.port.out.FindUserByInputPort;
import com.bb.envifastservice.application.port.out.InsertUserPort;
import com.bb.envifastservice.application.port.out.SearchUserLoginPort;
import com.bb.envifastservice.hexagonal.PersistenceAdapter;
import com.bb.envifastservice.models.AirportsModel;
import com.bb.envifastservice.models.RoleModel;
import com.bb.envifastservice.models.UserModel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@PersistenceAdapter
@RequiredArgsConstructor
public class UserAdapter implements InsertUserPort, SearchUserLoginPort, FindUserByInputPort {
    private final UserRepository userRepository;
    private final AirportRepository airportRepository;
    private final RoleRepository roleRepository;

    @Override
    public Usuario insertUser(Usuario usuario){
        var usuarioNuevo = new UserModel();
        usuarioNuevo.setName(usuario.getNombreUsuario());
        usuarioNuevo.setPLastName(usuario.getApellidoP());
        usuarioNuevo.setMLastName(usuario.getApellidoM());
        usuarioNuevo.setEmail(usuario.getCorreo());
        usuarioNuevo.setPhoneNumber(usuario.getTelefonoNumero());
        usuarioNuevo.setUsername(usuario.getNombreUsuario());
        usuarioNuevo.setPassword(usuario.getContrasenha());
        usuarioNuevo.setActive(1);

        if(usuario.getAeropuerto()!=null) {
            var airport = airportRepository.findByIdActive(usuario.getAeropuerto().getId().longValue(), 1);
            usuarioNuevo.setAirport(airport);
        }

        var roles = new ArrayList<RoleModel>();
        for (Rol rol: usuario.getRoles()
        ) {
            var role = roleRepository.findByIdActive(rol.getId().longValue(),1);
            if(role != null)
                roles.add(role);
        }
        usuarioNuevo.setRoles(roles);
        userRepository.save(usuarioNuevo);
        usuario.setId((int)(long)usuarioNuevo.getId());
        return usuario;
    }


    @Override
    public Usuario searchUserLogin(String username, String password){
        var usuariosBD = userRepository.findByUsernamePassword(username,password,1);
        var usuarioEncontrado = new Usuario();
        if(usuariosBD.size()==1){
            usuarioEncontrado.setId((int)(long)usuariosBD.get(0).getId());
            usuarioEncontrado.setNombres(usuariosBD.get(0).getName());
            usuarioEncontrado.setApellidoP(usuariosBD.get(0).getPLastName());
            usuarioEncontrado.setApellidoM(usuariosBD.get(0).getMLastName());
            usuarioEncontrado.setCorreo(usuariosBD.get(0).getEmail());
            usuarioEncontrado.setTelefonoNumero(usuariosBD.get(0).getPhoneNumber());
            usuarioEncontrado.setNombreUsuario(usuariosBD.get(0).getUsername());
            usuarioEncontrado.setContrasenha(usuariosBD.get(0).getPassword());
            var aeropuerto = new Aeropuerto();
            if(usuariosBD.get(0).getAirport()!= null){
                aeropuerto.setId((int)(long)usuariosBD.get(0).getAirport().getId());
                //Faltan mas campos
            }
            usuarioEncontrado.setAeropuerto(aeropuerto);
            var roles = new ArrayList<Rol>();
            if(usuariosBD.get(0).getRoles()!= null) {
                for(RoleModel role: usuariosBD.get(0).getRoles()) {
                    var rol = new Rol();
                    rol.setId(role.getId());
                    rol.setNombreRol(role.getName());
                    roles.add(rol);
                }
            }
            usuarioEncontrado.setRoles(roles);

        }
        else{
            usuarioEncontrado.setId(0);
        }
        return usuarioEncontrado;
    }



    @Override
    public List<Usuario> findUserByInput(String input) {
        var row = userRepository.queryUsersByParams(input);
        var aeropuerto = new Aeropuerto();
        var usuario = new Usuario();
        var usuarios = new ArrayList<Usuario>();
        for (UserModel user:row) {
            UserModelToUsuario(aeropuerto, usuario, user);
            usuarios.add(usuario);
        }
        return usuarios;
    }

    private void UserModelToUsuario(Aeropuerto aeropuerto, Usuario usuario, UserModel user) {
        var roles = new ArrayList<Rol>();
        usuario.setId(Math.toIntExact(user.getId()));
        usuario.setNombres(user.getName());
        usuario.setApellidoP(user.getPLastName());
        usuario.setApellidoM(user.getMLastName());
        usuario.setCorreo(user.getEmail());
        usuario.setTelefonoNumero(user.getPhoneNumber());
        usuario.setNombreUsuario(user.getUsername());
        aeropuerto.setId(Math.toIntExact(user.getAirport().getId()));
        aeropuerto.setCodigo(user.getAirport().getAirportCode());
        aeropuerto.setCapacidad(user.getAirport().getMaxCapacity());
        var ciudad = new Ciudad();
        ciudad.setAbreviacion(user.getAirport().getCityShortName());
        ciudad.setContinente(user.getAirport().getContinent());
        ciudad.setNombre(user.getAirport().getCityName());
        ciudad.setPais(user.getAirport().getCountryName());
        aeropuerto.setCiudad(ciudad);
        usuario.setAeropuerto(aeropuerto);
        for (RoleModel role: user.getRoles()) {
            var rol = new Rol();
            rol.setId(role.getId());
            rol.setNombreRol(role.getName());
            roles.add(rol);
        }
    }


}
