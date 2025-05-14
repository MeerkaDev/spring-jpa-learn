package org.mirasruntime.springjpa.repository;

import org.mirasruntime.springjpa.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByPriceBetween(Double lower, Double upper);

    List<Product> findByNameContainingIgnoreCase(String name);

    Optional<Product> findFirstByOrderByPriceDesc();

    @Query("select p from Product p " +
            "where (:categoryId is null or p.category.id = :categoryId) " +
            "and (:minPrice is null or p.price >= :minPrice) " +
            "and (:maxPrice is null or p.price <= :maxPrice) " +
            "and (:nameSearch is null or upper(p.name) like upper(concat('%', cast(:nameSearch as string), '%')))")
    Page<Product> findWithParams(@Param("categoryId") Long categoryId,
                                 @Param("minPrice") Double minPrice,
                                 @Param("maxPrice") Double maxPrice,
                                 String nameSearch,
                                 Pageable pageable);
}
