package com.featuresvote.repositories;

import com.featuresvote.domain.Product;
import com.featuresvote.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {


    List<Product> findByUser(User user);
}
