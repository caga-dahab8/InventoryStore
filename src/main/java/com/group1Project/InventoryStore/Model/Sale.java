package com.group1Project.InventoryStore.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data // getter and setter  | NoargConstructor and ArgConstructor
@Table(name = "SaleTble")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER) // Ensure eager fetching to load products
    @JoinColumn(name = "product_id", nullable = false)

    private Product product;
    private int quantitySold;
    private LocalDateTime saleDate;
}
