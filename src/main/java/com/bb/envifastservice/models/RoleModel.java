package com.bb.envifastservice.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name= "role")
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "_id_role")
    private Long id;
    @Column(name = "_name")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "role_users",
            joinColumns = @JoinColumn(name = "sysrole_id"),
            inverseJoinColumns = @JoinColumn(name = "sysuser_id")
    )
    private List<UserModel> role_users = new ArrayList<>();

    @Column(name = "_active")
    private int active;
}
