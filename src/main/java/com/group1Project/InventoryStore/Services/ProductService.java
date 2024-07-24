package com.group1Project.InventoryStore.Services;

import com.group1Project.InventoryStore.Model.Product;
import com.group1Project.InventoryStore.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Product product) {
        productRepository.save(product); // This works because save() will update if the entity has an ID
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public void sellProduct(Long id, int quantity) {
        Product product = productRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid product ID: " + id));
        if (product.getQuantity() < quantity) {
            throw new IllegalArgumentException("Not enough quantity available");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }

}
