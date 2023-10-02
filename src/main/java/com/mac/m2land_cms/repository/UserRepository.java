package com.mac.m2land_cms.repository;

import com.mac.m2land_cms.model.entity.User;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long>, JpaSpecificationExecutor<User> {

    Optional<User> findUsersByUsernameOrEmailOrPhone(@Size(max = 150) String username, @Size(max = 150) String email, @Size(max = 150) String phone);

    Optional<User> findUsersByUsername(@Size(max = 150) String username);

    @Query(value = "select COUNT(*) FROM users u WHERE (u.USERNAME = :username OR u.EMAIL = :email OR u.PHONE = :phoneNumber);",
            nativeQuery = true)
    Integer checkUserExist(@Param("username")String username,
                                  @Param("email")String email,
                                  @Param("phoneNumber")String phoneNumber);

    @Modifying(clearAutomatically=true, flushAutomatically = true)
    @Transactional
    @Query(value = "update " +
            "USERS " +
            "set " +
            "LAST_GEO_LAT = :lat, " +
            "LAST_GEO_LNG = :lng, " +
            "LAST_GEO_AT = now()  " +
            "where " +
            "USER_ID = :userId;", nativeQuery = true)
    void updateUserLocation(@Param("lat")String lat,@Param("lng")String lng,@Param("userId")Long userId);


    @Modifying(clearAutomatically=true, flushAutomatically = true)
    @Transactional
    @Query(value = "UPDATE users SET SUSPENSION_START = :suspensionStart, SUSPENSION_END = :suspensionEnd, REASON_SUSPENSION = :reasonSuspension WHERE ID = :id", nativeQuery = true)
    void UpdateSuspension(@Param("id") Long id, @Param("suspensionStart")LocalDateTime suspensionStart,
                          @Param("suspensionEnd") LocalDateTime suspensionEnd,
                          @Param("reasonSuspension") String reasonSuspension);


    @Query(value = "select * from users where PROVIDER = :searchProvider and USERNAME = :searchUsername", nativeQuery = true)
    Page<User> searchUserAccount(@Param("searchProvider") String searchProvider,
                                 @Param("searchUsername") String searchUsername,Pageable pageable);
}
