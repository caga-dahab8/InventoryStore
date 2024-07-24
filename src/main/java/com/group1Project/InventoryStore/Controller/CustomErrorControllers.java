package com.group1Project.InventoryStore.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

public interface CustomErrorControllers extends ErrorController {
    @RequestMapping("/error")
    String handleError(HttpServletRequest request, Model model);

    String getErrorPath();
}
