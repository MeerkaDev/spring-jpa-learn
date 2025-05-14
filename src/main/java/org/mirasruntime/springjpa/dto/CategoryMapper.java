package org.mirasruntime.springjpa.dto;

import lombok.RequiredArgsConstructor;
import org.mirasruntime.springjpa.model.Category;
import org.mirasruntime.springjpa.model.Option;
import org.springframework.stereotype.Component;
import org.mirasruntime.springjpa.dto.CategoryFullDto.OptionDto;

import java.util.List;

@Component
public class CategoryMapper {

    public CategoryShortDto toShortDto(Category category) {
        CategoryShortDto shortDto = new CategoryShortDto();
        shortDto.setId(category.getId());
        shortDto.setName(category.getName());

        return shortDto;
    }

    public List<CategoryShortDto> toShortDto(List<Category> categories) {
        return categories.stream()
                .map(this::toShortDto)
                .toList();
    }

    public CategoryFullDto toFullDto(Category category) {
        CategoryFullDto fullDto = new CategoryFullDto();

        fullDto.setId(category.getId());
        fullDto.setName(category.getName());
        fullDto.setOptions(toOptionDto(category.getOptions()));
        return fullDto;
    }

    private List<OptionDto> toOptionDto(List<Option> options) {
        return options.stream()
                .map(option -> {
                    OptionDto dto = new OptionDto();
                    dto.setName(option.getName());
                    dto.setId(option.getId());
                    return dto;
                })
                .toList();
    }

    public Category categoryCreateDtoToCategory(CategoryCreateDto categoryCreateDto) {
        Category category = new Category();
        category.setName(categoryCreateDto.getName());
        List<Option> options = categoryCreateDto.getOptions().stream()
                .map(optionName -> {
                    Option option = new Option();
                    option.setName(optionName);
                    option.setCategory(category);
                    return option;
                })
                .toList();
        category.setOptions(options);

        return category;
    }
}
