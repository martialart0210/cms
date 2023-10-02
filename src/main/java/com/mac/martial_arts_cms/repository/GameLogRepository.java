package com.mac.martial_arts_cms.repository;

import com.mac.martial_arts_cms.model.entity.GameLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The interface GameLogRepository.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Repository
public interface GameLogRepository extends JpaRepository<GameLog, Long> {

    List<GameLog> findByUserId(Long userId);
}
