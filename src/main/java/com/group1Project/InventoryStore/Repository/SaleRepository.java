package com.group1Project.InventoryStore.Repository;

import com.group1Project.InventoryStore.Model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale,Long> {
}
