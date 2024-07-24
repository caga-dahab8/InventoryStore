package com.group1Project.InventoryStore.CustomConverter;

import com.group1Project.InventoryStore.Model.Product;
import com.group1Project.InventoryStore.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToProductConverter implements Converter<String, Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product convert(String source) {
        try {
            Long id = Long.parseLong(source);
            return productRepository.findById(id).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
