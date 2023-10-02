package com.mac.m2land_cms.repository;

import com.mac.m2land_cms.model.entity.Dojo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface DojoRepository.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Repository
public interface DojoRepository extends JpaRepository<Dojo, String> {
}
