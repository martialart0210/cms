package com.mac.m2land_cms.repository;

import com.mac.m2land_cms.model.entity.PushNotification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface PushNotificationRepository.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Repository
public interface PushNotificationRepository extends JpaRepository<PushNotification, Long> {
}
