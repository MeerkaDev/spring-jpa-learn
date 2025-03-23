package org.mirasruntime.springjpa.controller;

import lombok.RequiredArgsConstructor;
import org.mirasruntime.springjpa.model.Product;
import org.mirasruntime.springjpa.repository.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    @GetMapping
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable long id) {
        return productRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Product create(@RequestBody Product product) {
        return productRepository.save(product);
    }

    @GetMapping("/between")
    public List<Product> findByPriceBetween(@RequestParam(name = "from") double low,
                                            @RequestParam(name = "to") double high) {
        return productRepository.findByPriceBetween(low, high);
    }

    @GetMapping("/search")
    public List<Product> findByNameContainingIgnoreCase(@RequestParam(name = "q")
                                                        String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/find-most-expensive")
    public Product findMostExpensive() {
        return productRepository.findFirstByOrderByPriceDesc().orElseThrow();
    }
}
