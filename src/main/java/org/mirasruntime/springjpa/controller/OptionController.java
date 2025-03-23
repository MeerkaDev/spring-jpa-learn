package org.mirasruntime.springjpa.controller;

import lombok.RequiredArgsConstructor;
import org.mirasruntime.springjpa.model.Option;
import org.mirasruntime.springjpa.repository.OptionRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/options")
public class OptionController {
    private final OptionRepository optionRepository;

    @GetMapping
    public List<Option> findAll(){
        return optionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Option findById(@PathVariable long id){
        return optionRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Option create(@RequestBody Option option){
        return optionRepository.save(option);
    }
}
