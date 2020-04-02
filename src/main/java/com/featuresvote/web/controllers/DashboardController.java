package com.featuresvote.web.controllers;

import com.featuresvote.domain.Product;
import com.featuresvote.domain.User;
import com.featuresvote.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final ProductRepository productRepository;

    @Autowired
    public DashboardController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping("/")
    public String rootView() {
        return "index";
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal User user, ModelMap model) {
        List<Product> products = productRepository.findByUser(user);
        model.put("products", products);

        return "dashboard";
    }
}
