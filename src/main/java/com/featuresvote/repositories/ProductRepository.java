package com.featuresvote.repositories;

import com.featuresvote.domain.Product;
import com.featuresvote.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p" + " JOIN fetch p.user" + " where p.id = :id")
    Optional<Product> findByIdWithUser(Long id);

    List<Product> findByUser(User user);
}
