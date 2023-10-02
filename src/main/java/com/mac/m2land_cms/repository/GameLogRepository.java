package com.mac.m2land_cms.repository;

import com.mac.m2land_cms.model.entity.GameLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface GameLogRepository.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Repository
public interface GameLogRepository extends JpaRepository<GameLog, Long> {

    Page<GameLog> findByUserId(Long userId, Pageable pageable);
}
