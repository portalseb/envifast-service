package com.bb.envifastservice.adapter.out.persistence.repos;
import com.bb.envifastservice.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
public interface RoleRepository extends JpaRepository<RoleModel,Long> {
    @Query("SELECT a from RoleModel a where a.id = :id and a.active = :active")
    RoleModel findByIdActive(Long id, int active);
}
