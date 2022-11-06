package com.bb.envifastservice.adapter.out.persistence.repos;
import com.bb.envifastservice.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long>{

    @Query("SELECT a from UserModel a where (a.email = :user or a.username = :user) and a.password = :pass and a.active = :active")
    List<UserModel> findByUsernamePassword(@Param("user") String user, @Param("pass") String pass, int active);
    Optional<UserModel> findByEmail(String email);
    @Query("Select u from UserModel u where u.active = 1 and lower(u.email) like lower(concat('%', :campos, '%')) or lower(u.username) like lower(concat('%', :campos, '%')) or lower(u.name) like lower(concat('%', :campos, '%')) or lower(u.mLastName) like lower(concat('%', :campos, '%')) or lower(u.pLastName) like lower(concat('%', :campos, '%')) or lower(u.phoneNumber) like lower(concat('%', :campos, '%')) or lower(u.email" +
            ") like lower(concat('%', :campos, '%'))")
    List<UserModel> queryUsersByParams(@Param("campos") String input);
    @Query("SELECT a from UserModel a where a.id = :idUser and a.active = :active")
    UserModel findByIdActive(@Param("idUser") Long idUser, int active);
}
