package org.mirasruntime.springjpa.controller;

import lombok.RequiredArgsConstructor;
import org.mirasruntime.springjpa.model.Product;
import org.mirasruntime.springjpa.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductRepository productRepository;

    /*
    @GetMapping
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    */

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

    @GetMapping
    public List<Product> findWithParams(@RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "10") int size,
                                        @RequestParam(name = "sort", defaultValue = "id,asc") String sort,
                                        @RequestParam(name = "categoryId", required = false) Long categoryId,
                                        @RequestParam(name = "minPrice", required = false) Double minPrice,
                                        @RequestParam(name = "maxPrice", required = false) Double maxPrice,
                                        @RequestParam(name = "nameSearch", required = false) String nameSearch) {

        String[] sortParams = sort.split(",");
        if(sortParams[0].equals("name"))
        {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        Sort sortObj = Sort.by(sortParams[1].equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC, sortParams[0]);
        Pageable pageable = PageRequest.of(page, size, sortObj);

        return productRepository.findWithParams(categoryId, minPrice, maxPrice, nameSearch, pageable).getContent();
    }
}
