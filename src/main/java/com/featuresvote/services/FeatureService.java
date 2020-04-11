package com.featuresvote.services;

import com.featuresvote.domain.Feature;
import com.featuresvote.domain.Product;
import com.featuresvote.repositories.FeatureRepository;
import com.featuresvote.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FeatureService {

    private final FeatureRepository featureRepository;
    private final ProductRepository productRepository;

    @Autowired
    public FeatureService(FeatureRepository featureRepository, ProductRepository productRepository) {
        this.featureRepository = featureRepository;
        this.productRepository = productRepository;
    }

    public Feature createFeature(Long productId) {
        Feature feature = new Feature();
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            feature.setProduct(product);
            product.getFeatures().add(feature);

            feature.setStatus("Pending review");
            return featureRepository.save(feature);
        }

        return feature;
    }
}
