package org.mirasruntime.springjpa.controller;

import lombok.RequiredArgsConstructor;
import org.mirasruntime.springjpa.model.Value;
import org.mirasruntime.springjpa.repository.ValueRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/values")
public class ValueController {
    private final ValueRepository valueRepository;

    @GetMapping
    public List<Value> findAll() {
        return valueRepository.findAll();
    }

    @GetMapping("/{id}")
    public Value findById(@PathVariable Long id) {
        return valueRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Value create(@RequestBody Value value) {
        return valueRepository.save(value);
    }
}
