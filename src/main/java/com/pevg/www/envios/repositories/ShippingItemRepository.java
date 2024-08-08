package com.pevg.www.envios.repositories;

import com.pevg.www.envios.dtos.ProductSummary;
import com.pevg.www.envios.entities.ShippingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShippingItemRepository extends JpaRepository<ShippingItem, Integer> {

    @Query(value = "SELECT p.description AS description, SUM(si.product_count) AS totalCount " +
            "FROM shipping_item si JOIN product p ON si.product_id = p.id " +
            "GROUP BY p.id, p.description " +
            "ORDER BY totalCount DESC " +
            "LIMIT 3", nativeQuery = true)
    List<ProductSummary> findTopProducts();


}
