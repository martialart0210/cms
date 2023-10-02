package com.mac.m2land_cms.repository;

import com.mac.m2land_cms.model.entity.UserCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * The Class UserCharacterRepository.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */

@Repository
public interface UserCharacterRepository extends JpaRepository<UserCharacter, Long> {

    /**
     *
     * @param userId
     * @return List<Optional<Object>>
     */
    Optional<UserCharacter> findByUserId(Long userId);

}
