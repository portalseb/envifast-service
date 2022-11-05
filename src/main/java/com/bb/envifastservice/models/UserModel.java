package com.bb.envifastservice.models;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name= "SysUser")
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
        @Column(name = "_email")
        private String email;
        @Column(name = "_phone_number")
        private String phoneNumber;
        @Column(name = "_username")
        private String username;
        @Column(name = "_password")
        private String password;
        @ManyToMany(mappedBy = "role_users")
        private List<RoleModel> roles= new ArrayList<>();
        //@ManyToMany(fetch = FetchType.EAGER)

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "idAirport", insertable = true, updatable = true)
        private AirportsModel airport;

        @Column(name = "_active")
        private int active;
}
