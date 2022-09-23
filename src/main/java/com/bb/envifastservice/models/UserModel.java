package com.bb.envifastservice.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name= "user")
public class UserModel {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "_id_user")
        private Long id;
        @Column(name = "_name")
        private String name;
        @Column(name = "_username")
        private String username;
        @Column(name = "_password")
        private String password;
        @ManyToMany(fetch = FetchType.EAGER)
        private Collection<RoleModel> = new ArrayList<>();
        @Column(name = "_active")
        private int active;
}
