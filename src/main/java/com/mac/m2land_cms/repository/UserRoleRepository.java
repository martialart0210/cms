package com.mac.m2land_cms.repository;


import com.mac.m2land_cms.model.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    List<UserRoleEntity> findAllByUserId(Long userId);

    @Transactional
    @Modifying
    void deleteAllByUserId(Long userId);
}
