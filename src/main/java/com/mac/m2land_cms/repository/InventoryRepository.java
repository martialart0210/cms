package com.mac.m2land_cms.repository;

import com.mac.m2land_cms.model.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The Class InventoryRepository.
 *
 * @author <a href="mailto:phuoc@dxplus.io">phuoc</a>
 */
@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    @Query(value = "SELECT MIN(i.ITEM_ID) as id, i.ITEM_NAME, COUNT(i.ITEM_NAME) AS count " +
            "FROM Items i " +
            "JOIN inventorys iv ON i.INVENTORY_ID = iv.INVENTORY_ID " +
            "WHERE i.USER_ID = :idUser GROUP BY i.ITEM_NAME " +
            "ORDER BY MIN(i.ITEM_ID)",
            nativeQuery = true)
    List<Optional<Object>> getItemByUserId(@Param("idUser") Long idUser);
}
