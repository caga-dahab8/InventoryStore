package com.group1Project.InventoryStore.Model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // getter and setter  | NoargConstructor and ArgConstructor
@Table(name = "ProductTble")

public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private double price;
    private int quantity;
}
