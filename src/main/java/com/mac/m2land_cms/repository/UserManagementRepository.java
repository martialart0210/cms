package com.mac.m2land_cms.repository;

import com.mac.m2land_cms.model.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * The interface UserManagementRepository.
 *
 * @author <a href="mailto:an@dxplus.io">an</a>
 */
@Repository
public interface UserManagementRepository extends JpaRepository<User, Long> {
    @Query(value = "SELECT * FROM users\n" +
            "WHERE  ROLE IN('Super admin' ,'Normal admin') and status NOT IN (4) ",
            nativeQuery = true)
    Page<User> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM users\n" +
            "WHERE  date_format(:dateTime, '%Y-%m-%d') between SUSPENSION_START and SUSPENSION_END",
            nativeQuery = true)
    Page<User> findAllSuspension(LocalDateTime dateTime, Pageable pageable);

    @Query(value = "SELECT * FROM users WHERE UUID = :uuid", nativeQuery = true)
    Optional<User> findByUuid(String uuid);

    @Query(value = "SELECT * FROM users\n" +
            "WHERE MAXIMUM_ACCESS_USER is not null",nativeQuery = true)
    Page<User> findAllAccess(Pageable pageable);

    @Query(value = "SELECT * FROM users WHERE USERNAME = :userName", nativeQuery = true)
    Optional<User> findByUserName(String userName);
}
