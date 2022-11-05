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
}
