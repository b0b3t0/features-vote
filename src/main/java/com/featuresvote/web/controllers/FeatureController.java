package com.featuresvote.web.controllers;

import com.featuresvote.services.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {

    private final FeatureService featureService;

    @Autowired
    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @PostMapping("/")
    public String createFeature(@PathVariable Long productId) {
        featureService.createFeature(productId);

        return "feature";
    }
}
