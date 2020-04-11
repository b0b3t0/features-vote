package com.featuresvote.repositories;

import com.featuresvote.domain.Feature;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeatureRepository extends JpaRepository<Feature, Long> {


}
