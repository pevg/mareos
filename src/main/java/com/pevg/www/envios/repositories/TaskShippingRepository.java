package com.pevg.www.envios.repositories;

import com.pevg.www.envios.entities.TaskShipping;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskShippingRepository extends JpaRepository<TaskShipping, Integer> {
    @Query("SELECT ts FROM task_shipping ts WHERE ts.shipping.id = :shippingId AND ts.end_date IS NULL ORDER BY ts.start_date DESC")
    Optional<TaskShipping> findByShippingId(@Param("shippingId") int shippingId);
}
