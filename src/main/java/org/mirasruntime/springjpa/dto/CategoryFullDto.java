package org.mirasruntime.springjpa.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryFullDto {
    long id;
    String name;
    List<OptionDto> options;

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class OptionDto {
        long id;
        String name;
    }
}
