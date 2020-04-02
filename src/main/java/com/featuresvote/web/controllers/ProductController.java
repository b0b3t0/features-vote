package com.featuresvote.web.controllers;

import com.featuresvote.domain.Product;
import com.featuresvote.domain.User;
import com.featuresvote.repositories.ProductRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @GetMapping("/products")
    public String getProducts(ModelMap model) {
        return "product";
    }

    @GetMapping("/products/{productId}")
    public String getProduct(@PathVariable Long productId, ModelMap model, HttpServletResponse response) throws IOException {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();
            model.put("product", product);
        } else {
            response.sendError(HttpStatus.NOT_FOUND.value(), "Product with id " + productId + " is not found");
            return "product";
        }

        return "product";
    }

    @PostMapping("/products")
    public String createProduct(@AuthenticationPrincipal User user){
        Product product = new Product();
        product.setPublished(false);
        product.setUser(user);

        product = productRepository.save(product);

        return "redirect:/products/" + product.getId();
    }

    @PostMapping("/products/{productId}")
    public String saveProduct(@PathVariable Long productId, Product product) {
        productRepository.save(product);

        return "redirect:/products/" + product.getId();
    }
}
