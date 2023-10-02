package com.mac.martial_arts_cms.repository;

import com.mac.martial_arts_cms.model.entity.PushNotification;
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
