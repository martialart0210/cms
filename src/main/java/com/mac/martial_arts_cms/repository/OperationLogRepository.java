package com.mac.martial_arts_cms.repository;

import com.mac.martial_arts_cms.model.entity.OperationLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The interface OperationLogRepository.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Repository
public interface OperationLogRepository extends JpaRepository<OperationLog, Long> {

    List<OperationLog> findByUserId(Long userId);
}
