package com.group1Project.InventoryStore.Controller;

import com.group1Project.InventoryStore.Model.Product;
import com.group1Project.InventoryStore.Model.Sale;
import com.group1Project.InventoryStore.Repository.ProductRepository;
import com.group1Project.InventoryStore.Repository.SaleRepository;
import com.group1Project.InventoryStore.Services.SaleService;
import com.group1Project.InventoryStore.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/sales") //localhost:8080/sales
public class SaleController {
    @Autowired
    private SaleService saleService;

    @Autowired
    private ProductService productService;

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public String listSales(Model model) {
        model.addAttribute("sales", saleService.getAllSales());
        return "sale/list";
    }

    @GetMapping("/new")
    public String showSaleForm(Model model) {
        model.addAttribute("sale", new Sale());
        model.addAttribute("products", productRepository.findAll());
        return "sale/form";
    }

    @PostMapping
    public String saveSale(@ModelAttribute @Validated Sale sale, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("products", productRepository.findAll());
            return "sale/form";
        }

        boolean isSaved = saleService.saveSale(sale);
        if (!isSaved) {
            result.rejectValue("quantitySold", "error.sale", "Insufficient product quantity for the sale.");
            model.addAttribute("products", productRepository.findAll());
            return "sale/form";
        }

        return "redirect:/sales";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Sale sale = saleService.getSaleById(id);

        if (sale == null) {
            return "redirect:/sales";
        }
        model.addAttribute("sale", sale);
        model.addAttribute("products", productRepository.findAll());
        return "sale/form";
    }

    @PostMapping("/edit/{id}")
    public String updateSale(@PathVariable Long id, @ModelAttribute("sale") Sale sale, BindingResult result, Model model) {
        sale.setId(id);

        boolean isUpdated = saleService.updateSale(sale);
        if (!isUpdated) {
            result.rejectValue("quantitySold", "error.sale", "Insufficient product quantity for the sale.");
            model.addAttribute("products", productRepository.findAll());
            return "sale/form";
        }

        return "redirect:/sales";
    }

    @GetMapping("/delete/{id}")
    public String deleteSale(@PathVariable Long id) {
        saleService.deleteSale(id);
        return "redirect:/sales";
    }
}