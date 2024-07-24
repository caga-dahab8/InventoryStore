package com.group1Project.InventoryStore.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/aboutus")
public class Aboutus {

    @GetMapping
    public String listProducts(Model model) {
        return "aboutus/about";
    }

}
