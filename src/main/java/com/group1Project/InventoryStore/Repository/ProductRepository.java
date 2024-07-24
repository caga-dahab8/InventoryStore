package com.group1Project.InventoryStore.Repository;

import com.group1Project.InventoryStore.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
