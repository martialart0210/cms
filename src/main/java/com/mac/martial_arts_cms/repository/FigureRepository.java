package com.mac.martial_arts_cms.repository;

import com.mac.martial_arts_cms.model.entity.Figure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The Class FigureRepository.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */

@Repository
public interface FigureRepository extends JpaRepository<Figure, Long> {

    /**
     *
     * @param username
     * @return List<Optional<Object>>
     */
    @Query(value = "select f.CHARACTER_NAME as characterName, f.GOLD as gold, g.GUILD_NAME as guildName from figures f left join guilds g on f.CHARACTER_ID = g.GUILD_ID left join users u on f.USER_ID = u.ID where u.USERNAME  = :username", nativeQuery = true )
    List<Optional<Object>> getInfoFigureByUsername(@Param("username") String username);

}
