package com.mac.martial_arts_cms.repository;

import com.mac.martial_arts_cms.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

/**
 * The interface UserManagementRepository.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Repository
public interface UserManagementRepository extends JpaRepository<User, Long> {
    Page<User> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM martial_arts_dev.users\n" +
            "WHERE  date_format(:dateTime, '%Y-%m-%d') between SUSPENSION_START and SUSPENSION_END",
            nativeQuery = true)
    Page<User> findAllSuspension(LocalDateTime dateTime, Pageable pageable);
}
