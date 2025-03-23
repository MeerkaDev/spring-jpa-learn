package org.mirasruntime.springjpa.repository;

import org.mirasruntime.springjpa.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByPriceBetween(Double lower, Double upper);

    List<Product> findByNameContainingIgnoreCase(String name);

    Optional<Product> findFirstByOrderByPriceDesc();
}
