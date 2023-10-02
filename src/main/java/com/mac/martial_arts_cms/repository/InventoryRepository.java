package com.mac.martial_arts_cms.repository;

import com.mac.martial_arts_cms.model.entity.Inventory;
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

    @Query(value = "SELECT t.INVENTORY_ID, t.ITEN_NAME, COUNT(t.ITEM_ID) AS countItemName " +
            "FROM inventorys i LEFT JOIN Items t ON t.INVENTORY_ID = i.INVENTORY_ID " +
            "WHERE i.USER_ID = :idUser " +
            "GROUP BY t.INVENTORY_ID, t.ITEN_NAME " +
            "ORDER BY t.INVENTORY_ID",
            nativeQuery = true)
    List<Optional<Object>> getItemByUserId(@Param("idUser") Long idUser);
}
