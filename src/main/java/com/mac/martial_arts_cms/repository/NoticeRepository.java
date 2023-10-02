package com.mac.martial_arts_cms.repository;

import com.mac.martial_arts_cms.model.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface InventoryRepository.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
