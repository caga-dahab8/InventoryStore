package com.group1Project.InventoryStore.Services;

import com.group1Project.InventoryStore.Model.Product;
import com.group1Project.InventoryStore.Model.Sale;
import com.group1Project.InventoryStore.Repository.ProductRepository;
import com.group1Project.InventoryStore.Repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(Long id) {
        return saleRepository.findById(id).orElse(null);
    }

    public boolean saveSale(Sale sale) {
        Product product = sale.getProduct();
        int updatedQuantity = product.getQuantity() - sale.getQuantitySold();

        if (updatedQuantity < 0) {
            return false; // Indicate failure due to insufficient quantity
        }

        product.setQuantity(updatedQuantity);
        productRepository.save(product);

        saleRepository.save(sale);
        return true; // Indicate success
    }

    public boolean updateSale(Sale sale) {
        // Get the existing sale to compare quantities
        Sale existingSale = saleRepository.findById(sale.getId()).orElse(null);
        if (existingSale == null) {
            return false; // Sale does not exist
        }

        Product product = sale.getProduct();
        int oldQuantitySold = existingSale.getQuantitySold();
        int newQuantitySold = sale.getQuantitySold();

        // Adjust the product quantity based on the change
        int quantityChange = oldQuantitySold - newQuantitySold;
        int updatedQuantity = product.getQuantity() + quantityChange;

        if (updatedQuantity < 0) {
            return false; // Indicate failure due to insufficient quantity
        }

        product.setQuantity(updatedQuantity);
        productRepository.save(product);

        saleRepository.save(sale);
        return true; // Indicate success
    }

    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }
}