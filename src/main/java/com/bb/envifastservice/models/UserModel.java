package com.bb.envifastservice.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name= "SysUser",
        indexes = {@Index(name = "emailIndex", columnList = "email", unique = true)})
public class UserModel {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        @Column(name = "_id_user")
        private Long id;
        @Column(name = "_name")
        private String name;
        @Column(name = "_p_last_name")
        private String pLastName;
        @Column(name = "_m_last_name")
        private String mLastName;
        @Column(name = "email")
        private String email;
        @Column(name = "_phone_number")
        private String phoneNumber;
        @Column(name = "_username")
        private String username;
        @Column(name = "_password")
        private String password;

        @ManyToMany
        @JoinTable(
                name = "user_roles",
                joinColumns = @JoinColumn(name = "sysuser_id"),
                inverseJoinColumns = @JoinColumn(name = "sysrole_id")
        )
        private List<RoleModel> user_roles= new ArrayList<>();
        //@ManyToMany(fetch = FetchType.EAGER)

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "idAirport", insertable = true, updatable = true)
        private AirportsModel airport;

        @Column(name = "_active")
        private int active;
}
