package main.controller;

import main.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/category/{name}")
    public String info(@PathVariable String name){
        return categoryService.info(name);
    }
}
