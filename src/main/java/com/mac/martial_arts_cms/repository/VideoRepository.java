package com.mac.martial_arts_cms.repository;

import com.mac.martial_arts_cms.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface VideoRepository.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
}
