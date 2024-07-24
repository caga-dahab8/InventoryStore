package com.group1Project.InventoryStore.Controller;

import com.group1Project.InventoryStore.Model.Product;
import com.group1Project.InventoryStore.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/products") //localhost:8080/products
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "product/list";
    }

    @GetMapping("/new") //localhost:8080/products/new
    public String showProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "product/form";
    }

    @PostMapping
    public String saveProduct(@ModelAttribute Product product) {
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product/form";
    }

    @PostMapping("/edit/{id}")
    public String updateProduct(@PathVariable Long id, @ModelAttribute("product") Product product) {
        product.setId(id); // Ensure the ID is set
        productService.updateProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/products";
    }

    @PostMapping("/sell/{id}")
    public String sellProduct(@PathVariable Long id, @RequestParam int quantity) {
        productService.sellProduct(id, quantity);
        return "redirect:/products";
    }
}
