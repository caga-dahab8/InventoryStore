package com.group1Project.InventoryStore.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements CustomErrorControllers {

    @Override
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("javax.servlet.error.status_code");
        Object message = request.getAttribute("javax.servlet.error.message");

        int statusCode = status != null ? Integer.parseInt(status.toString()) : 500;
        String errorMessage = message != null ? message.toString() : "Unexpected error occurred. Please try again later.";

        model.addAttribute("statusCode", statusCode);
        model.addAttribute("message", errorMessage);

        return "error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}