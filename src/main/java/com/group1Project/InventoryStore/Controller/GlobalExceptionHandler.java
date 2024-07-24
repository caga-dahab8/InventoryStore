package com.group1Project.InventoryStore.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("statusCode", 500);
        model.addAttribute("message", e.getMessage());
        return "error";
    }
}
