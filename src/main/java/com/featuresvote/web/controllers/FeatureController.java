package com.featuresvote.web.controllers;

import com.featuresvote.domain.Feature;
import com.featuresvote.domain.User;
import com.featuresvote.services.FeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.codec.EncodingException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Controller
@RequestMapping("/products/{productId}/features")
public class FeatureController {

    private final FeatureService featureService;

    @Autowired
    public FeatureController(FeatureService featureService) {
        this.featureService = featureService;
    }

    @PostMapping("")
    public String createFeature(@AuthenticationPrincipal User user, @PathVariable Long productId) {
        Feature feature = featureService.createFeature(productId, user);

        return "redirect:/products/" + productId + "/features/" + feature.getId();
    }

    @GetMapping("{featureId}")
    public String getFeature(@AuthenticationPrincipal User user, ModelMap model,
                             @PathVariable Long featureId, @PathVariable Long productId) {
        Optional<Feature> featureOpt = featureService.findById(featureId);

        if (featureOpt.isPresent()) {
            model.put("feature", featureOpt.get());
        }
        model.put("user", user);

        return "feature";
    }

    @PostMapping("{featureId}")
    public String updateFeature(@AuthenticationPrincipal User user, Feature feature,
                                @PathVariable Long productId, @PathVariable Long featureId) {
        feature.setUser(user);
        feature = featureService.save(feature);
        String encodedProductName;

        try {
            encodedProductName = URLEncoder.encode(feature.getProduct().getName(), StandardCharsets.UTF_8);
        } catch (EncodingException e) {
            return "redirect:/dashboard";
        }

        return "redirect:/p/" + encodedProductName;
    }
}
