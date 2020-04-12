package com.featuresvote.services;

import com.featuresvote.domain.Feature;
import com.featuresvote.domain.Product;
import com.featuresvote.domain.User;
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

    public Feature createFeature(Long productId, User user) {
        Feature feature = new Feature();
        Optional<Product> productOpt = productRepository.findById(productId);

        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            feature.setProduct(product);
            product.getFeatures().add(feature);

            feature.setUser(user);
            user.getFeatures().add(feature);

            feature.setStatus("Pending review");
            return featureRepository.save(feature);
        }

        return feature;
    }

    public Feature save(Feature feature) {

        return featureRepository.save(feature);
    }

    public Optional<Feature> findById(Long featureId) {
        return featureRepository.findById(featureId);
    }
}
