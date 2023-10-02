package com.mac.m2land_cms.repository;

import com.mac.m2land_cms.model.entity.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The Class UserCharacterRepository.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */

@Repository
public interface TopicRepository extends JpaRepository<Topic,Long> {

    List<Topic> findAllByUserDeviceToken(String deviceToken);
}
