package com.bb.envifastservice.models;

import lombok.Data;

import javax.persistence.*;

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
    @Column(name = "_active")
    private int active;
}