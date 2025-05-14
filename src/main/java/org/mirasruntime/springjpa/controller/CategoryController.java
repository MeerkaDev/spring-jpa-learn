package org.mirasruntime.springjpa.controller;

import lombok.RequiredArgsConstructor;
import org.mirasruntime.springjpa.dto.CategoryCreateDto;
import org.mirasruntime.springjpa.dto.CategoryFullDto;
import org.mirasruntime.springjpa.dto.CategoryMapper;
import org.mirasruntime.springjpa.dto.CategoryShortDto;
import org.mirasruntime.springjpa.model.Category;
import org.mirasruntime.springjpa.repository.CategoryRepository;
import org.mirasruntime.springjpa.repository.OptionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryRepository categoryRepository;
    private final OptionRepository optionRepository;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public List<CategoryShortDto> findAll() {
        return categoryMapper.toShortDto(categoryRepository.findAll());
    }

    @GetMapping("/{id}")
    public CategoryFullDto findById(@PathVariable long id) {
        return categoryMapper.toFullDto(categoryRepository.findById(id).orElseThrow());
    }

    @PostMapping
    public CategoryFullDto create(@RequestBody CategoryCreateDto createCategory) {
        Category category = categoryMapper.categoryCreateDtoToCategory(createCategory);

        categoryRepository.save(category);
        optionRepository.saveAll(category.getOptions());

        return categoryMapper.toFullDto(category);
    }
}
