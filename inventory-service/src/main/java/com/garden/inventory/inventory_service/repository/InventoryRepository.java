package com.garden.inventory.inventory_service.repository;

import com.garden.inventory.inventory_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findBySkuCodeIn(List<String> skuCode);
}
